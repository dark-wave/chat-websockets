import 'package:flutter/material.dart';

class ChatPage extends StatefulWidget {
  const ChatPage({super.key});

  @override
  State<ChatPage> createState() => _ChatPageState();
}

class _ChatPageState extends State<ChatPage> {
  final TextEditingController _textController = TextEditingController();
  final FocusNode _focusNode = FocusNode();

  final List<String> _messageList = [];

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
              physics: const BouncingScrollPhysics(),
              itemCount: _messageList.length,
              itemBuilder: (context, i) => 
              Container(
                padding: const EdgeInsets.all(8),
                margin: const EdgeInsets.only(
                  right: 5,
                  bottom: 5,
                  left: 50
                ),
                decoration: BoxDecoration(
                  color: const Color(0xff4D9EF6),
                  borderRadius: BorderRadius.circular(20)
                ),
                child: Text(_messageList[i], style: const TextStyle(color: Colors.white))
              ),
              reverse: true,
            ),
          ),
          const Divider(height: 1),
          SafeArea(
            child: Container(
              margin: const EdgeInsets.symmetric(horizontal: 8.0),
              child: Row(
                children: [
                  Flexible(
                    child: TextField(
                      controller: _textController,
                      decoration: const InputDecoration.collapsed(
                        hintText: 'Escribe tu mensaje'
                      ),
                      focusNode: _focusNode,
                    ),
                  ),
                  Container(
                    margin: const EdgeInsets.symmetric(horizontal: 4.0),
                    child: IconTheme(
                      data: IconThemeData(color: Colors.blue[400]),
                      child: IconButton(
                        highlightColor: Colors.transparent,
                        splashColor: Colors.transparent,
                        icon: const Icon(Icons.send),
                        onPressed: () => _envioMensaje(_textController.text.trim()), 
                      ),
                    )
                  )
                ],
              )
            )
          )
        ],
      )
    );
  }

  void _envioMensaje(String mensaje){
    if(mensaje.trim().isEmpty) return;

    _textController.clear();
    _focusNode.requestFocus();

    setState(() {
      _messageList.add(mensaje);
    });
  }

  @override
  void dispose() {
    _textController.dispose();
    super.dispose();
  }
}