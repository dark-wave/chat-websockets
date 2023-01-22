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

  StompClient get stompClient => _stompClient;

  void connectStomp(){
    _stompClient = StompClient(
      config: StompConfig(
        url: Environment.socketUrl,
        onConnect: _onConnect,
        onWebSocketError: (dynamic error) => print(error),
        onStompError: (StompFrame frame) => print(frame.body)
      )
    );

    _stompClient.activate();
  }

  void sendMessage(String message){
    Message messageBody = Message(
      uidSender: 'flutter_sender', 
      uidReceiver: 'flutter_receiver', 
      message: message
    );

    _stompClient.send(
      destination: '/sendMessage',
      body: json.encode(messageBody.toJson())
    );
  }

  void disconnectStomp(){
    _stompClient.deactivate();
  }

  void _onConnect(StompFrame frame){
    _stompClient.subscribe(
      destination: '/topic/message', 
      callback: (frame) => print('Mensaje')
    );
  }
}