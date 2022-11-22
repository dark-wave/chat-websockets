import 'package:flutter/material.dart';

void main() => runApp(const ChatApp());

class ChatApp extends StatelessWidget {
  const ChatApp({super.key});

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      title: 'Chat App',
      home: Scaffold(
        appBar: AppBar(
          title: const Text('Chat App'),
        ),
        body: const Center(
          child: Text('Aplicación de chat con sockets'),
        ),
      ),
    );
  }
}