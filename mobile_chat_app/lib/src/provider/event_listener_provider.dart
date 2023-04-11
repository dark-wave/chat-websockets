import 'package:flutter/material.dart';
import 'package:mobile_chat_app/src/environment/environment.dart';
import 'package:stomp_dart_client/stomp.dart';
import 'package:stomp_dart_client/stomp_config.dart';
import 'package:stomp_dart_client/stomp_frame.dart';

class EventListenerProvider with ChangeNotifier {
  late StompClient  _stompClient;
  late String _userUuid;


  void connectEventStomp(String userUuid){
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

  void _onConnect(StompFrame connectFrame) {
    _stompClient.subscribe(
      destination: '/user/$_userUuid/events/event',
      callback: (StompFrame frame) {
        
      }
    );
  }

  void disconnectEventStomp(){
    _stompClient.deactivate();
  }
}