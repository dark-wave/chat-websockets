import 'package:flutter/material.dart';
import 'package:stomp_dart_client/stomp.dart';

class ContactsProvider with ChangeNotifier{
  late StompClient  _stompClient;
}