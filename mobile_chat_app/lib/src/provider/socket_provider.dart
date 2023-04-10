import 'dart:convert';

import 'package:flutter/material.dart';
import 'package:mobile_chat_app/src/environment/environment.dart';
import 'package:mobile_chat_app/src/models/message.dart';
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

  void connectStomp(String userUuid){
    _userUuid = userUuid;
    _stompClient = StompClient(
      config: StompConfig.SockJS(
        url: Environment.socketUrl,
        onConnect: _onConnect,
        onWebSocketError: (dynamic error) => print(error), //TODO: tratamiento de error
        onStompError: (StompFrame frame) => print(frame.body)//TODO: tratamiento de error
      )
    );

    _stompClient.activate();
  }

   void _onConnect(StompFrame connectFrame){
    _stompClient.subscribe(
      destination: '/user/$_userUuid/queue/messages',
      callback: (StompFrame frame){
        Message message = Message.fromJson(json.decode(frame.body!));

        _messageList.add(message);

        notifyListeners();
      }
    );
  }

  void sendMessage(Message message){
    _messageList.add(message);

    _stompClient.send(
      destination: '/app/sendMessage/${message.uidReceiver}',
      body: json.encode(message.toJson())
    );

    notifyListeners();
  }

  void clearMessageList(){
    _messageList = [];
  }

  void disconnectStomp(){
    _stompClient.deactivate();
  }
}