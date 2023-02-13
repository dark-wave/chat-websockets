import 'dart:convert';

import 'package:flutter/material.dart';
import 'package:mobile_chat_app/src/environment/environment.dart';
import 'package:mobile_chat_app/src/models/message.dart';
import 'package:stomp_dart_client/stomp.dart';
import 'package:stomp_dart_client/stomp_config.dart';
import 'package:stomp_dart_client/stomp_frame.dart';

enum ServerStatus{
  online,
  offline,
  connecting
}

class SocketProvider with ChangeNotifier{
  late StompClient  _stompClient;
  List<Message> _messageList = [];

  StompClient get stompClient => _stompClient;

  List<Message> get messageList => _messageList;

  void connectStomp(){
    _stompClient = StompClient(
      config: StompConfig.SockJS(
        url: Environment.socketUrl,
        onConnect: _onConnect,
        onWebSocketError: (dynamic error) => print(error),
        onStompError: (StompFrame frame){
          print(frame.body);
        }
      )
    );

    _stompClient.activate();
  }

   void subscribeQueue(String userUuid){
    //'username': '2dac2e0c-11e4-4de4-b024-7b0700c178a9'
    _stompClient.subscribe(
      destination: '/queue/specific-user', 
      headers: {
        'ack': 'auto'
      },
      callback: (frame){
        Message _message = Message.fromJson(json.decode(frame.body!));

        _messageList.add(_message);

        notifyListeners();
      }
    );
  }

  void sendMessage(Message message){
    _stompClient.send(
      destination: '/app/sendMessage/2dac2e0c-11e4-4de4-b024-7b0700c178a9',
      body: json.encode(message.toJson())
    );
  }

  void disconnectStomp(){
    _stompClient.deactivate();
  }

  void _onConnect(StompFrame frame){
    print('Nos hemos conectado');
  }
}