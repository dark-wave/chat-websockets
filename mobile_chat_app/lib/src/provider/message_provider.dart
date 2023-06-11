import 'package:flutter/material.dart';
import 'package:mobile_chat_app/src/dto/message.dart';

class MessageProvider extends ChangeNotifier{
  static List<Message> _messageList = [];
  static Message? _message;

  List<Message> get messageList => _messageList;
  Message get message => _message!;
}