import 'package:flutter/material.dart';

class ChatPage extends StatefulWidget {
  const ChatPage({super.key});

  @override
  State<ChatPage> createState() => _ChatPageState();
}

class _ChatPageState extends State<ChatPage> {
  final List<String> _messageList = [
    'Hola este es el mensaje 1',
    'Hola este es el mensaje 2'
    'Hola este es el mensaje 3'
    'Hola este es el mensaje 4'
    'Hola este es el mensaje 5'
    'Hola este es el mensaje 6'
    'Hola este es el mensaje 7'
    'Hola este es el mensaje 8'
    'Hola este es el mensaje 9'
    'Hola este es el mensaje 10'
    'Hola este es el mensaje 11'
    'Hola este es el mensaje 12'
    'Hola este es el mensaje 13'
    'Hola este es el mensaje 14'
    'Hola este es el mensaje 15'
    'Hola este es el mensaje 16'
    'Hola este es el mensaje 17'
  ];

  @override
  Widget build(BuildContext context) {
    final name = ModalRoute.of(context)?.settings.arguments as String?;

    return Scaffold(
      appBar: AppBar(
        centerTitle: true,
        elevation: 1,
        title: Column(
          children: [
            CircleAvatar(
              backgroundColor: Colors.blue[100],
              maxRadius: 14,
              child: Text(name ?? ''.substring(0,2), style: const TextStyle(fontSize: 12)),
            ),
            const SizedBox(height: 3),
            Text(name ?? '', style: const TextStyle(color: Colors.black87, fontSize: 12)),
          ],
        ),
      ),
      body: Column(
        children: [
          Flexible(
            child: ListView.builder(
              itemCount: _messageList.length,
              itemBuilder: (context, i) => Text(_messageList[i]),
              reverse: true,
            ),
          )
        ],
      )
    );
  }
}