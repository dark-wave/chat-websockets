import 'dart:convert';

import 'package:flutter/material.dart';
import 'package:mobile_chat_app/src/environment/environment.dart';
import 'package:mobile_chat_app/src/dto/message.dart';
import 'package:mobile_chat_app/src/service/notification_service.dart';
import 'package:stomp_dart_client/stomp.dart';
import 'package:stomp_dart_client/stomp_config.dart';
import 'package:stomp_dart_client/stomp_frame.dart';
import 'package:http/http.dart' as http;

enum ServerStatus{
  online,
  offline,
  connecting
}

class SocketProvider with ChangeNotifier{
  late StompClient  _stompClient;
  List<Message> _messageList = [];
  late String _userUuid;

  StompClient get stompClient => _stompClient;

  List<Message> get messageList => _messageList;

  // Método que devuelve los ultimos mensajes enviados
  void loadLastMessages(String uuidSender, String uuidReceiver) async{
    final client = http.Client();

    final messageServiceResponse = await client.get(
      Uri.parse('${Environment.apiUrl} ${Environment.mesagesEndPoint}/$uuidSender/$uuidReceiver'),
      headers: { 
        'Content-Type':'application/json',
        'Accept': 'application/json'
      }
    );

    if(messageServiceResponse.statusCode == 200){
      if(messageServiceResponse.body.isNotEmpty){
        final List<dynamic> messageList = json.decode(messageServiceResponse.body);

        _messageList = messageList.map((message) => Message.fromJson(message)).toList();

        notifyListeners();
      }
    }
  }

  //Método de conexión al socket
  void connectStomp(String userUuid) async{
    _userUuid = userUuid;
    _stompClient = StompClient(
      config: StompConfig.SockJS(
        url: Environment.socketUrl,
        onConnect: _onConnect,
        onWebSocketError: (dynamic error) => print(error),      //TODO: tratamiento de error
        onStompError: (StompFrame frame) => print(frame.body),  //TODO: tratamiento de error
        onDisconnect: _onDisconnect
      )
    );
    
    _stompClient.activate();

    //Esperamos un tiempo a que se establezca la conexión con el socket
    await Future.delayed(const Duration(seconds: 1));

    //Enviamos un mensaje con nuestro user uuid para que el servidor nos asocie al socket
    _stompClient.send(
      destination: '/app/connect',
      body: userUuid
    );
  }

  //Método de desconexión del socket
  void disconnectStomp(){  
    _stompClient.deactivate();
  }

  void _onConnect(StompFrame connectFrame){
    _stompClient.activate();

    //Nos suscribimos a la cola de mensajes del usuario
    _stompClient.subscribe(
      destination: '/user/$_userUuid/queue/messages',
      callback: (StompFrame frame){
        Message message = Message.fromJson(json.decode(frame.body!));

        _messageList.add(message);

        notifyListeners();
      }
    );
    
    _stompClient.subscribe(
      destination: '/user/$_userUuid/contacts', 
      callback: (StompFrame frame){
        NotificationService().showLocalNotification('Solicitud de contacto', frame.body!);
      }
    );
  }

  void _onDisconnect(StompFrame connectFrame){
    notifyListeners();
  } 

  //Método para enviar un mensaje al servidor de socket
  void sendMessage(Message message){
    _messageList.add(message);

    _stompClient.send(
      destination: '/app/sendMessage/${message.uidReceiver}',
      body: json.encode(message.toJson())
    );

    notifyListeners();
  }

  //Método para enviar un mensaje al servidor para asociar el usuario al socket
  void sendUserUuid(String userUuid){
    _stompClient.send(
      destination: '/app/connect',
      body: userUuid
    );
  }

  //Método para limpiar la lista de mensajes
  void clearMessageList(){
    _messageList = [];
  }
}