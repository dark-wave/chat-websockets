import 'package:flutter/material.dart';
import 'package:flutter/scheduler.dart';
import 'package:mobile_chat_app/src/provider/socket_provider.dart';
import 'package:provider/provider.dart';


class ChatPage extends StatefulWidget {
  const ChatPage({super.key});

  @override
  State<ChatPage> createState() => _ChatPageState();
}

class _ChatPageState extends State<ChatPage> {
  final TextEditingController _textController = TextEditingController();
  final ScrollController _scrollController = ScrollController();
  final FocusNode _focusNode = FocusNode();

  late SocketProvider socketProvider;

  @override
  void initState() {
    super.initState();
    
    socketProvider = Provider.of<SocketProvider>(context, listen: false);
  }

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
              child: Text(name!.substring(0,2), style: const TextStyle(fontSize: 12)),
            ),
            const SizedBox(height: 3),
            Text(name, style: const TextStyle(color: Colors.black87, fontSize: 12)),
          ],
        ),
      ),
      body: Column(
        children: [
          Expanded(
            child: Consumer<SocketProvider>(
              builder: (context, data, child){
                var messageList = data.messageList;

                if(messageList.isNotEmpty){
                  SchedulerBinding.instance.addPostFrameCallback((_) {
                    _scrollController.animateTo(
                    _scrollController.position.maxScrollExtent,
                    duration: const Duration(milliseconds: 1),
                    curve: Curves.fastOutSlowIn);
                  });
                  return Padding(
                    padding: const EdgeInsets.all(10.0),
                    child: SingleChildScrollView(
                      child: ListView.builder(
                        controller: _scrollController,
                        shrinkWrap: true,
                        physics: const BouncingScrollPhysics(),
                        itemCount: messageList.length,
                        reverse: false,
                        itemBuilder: (context, index){
                          return Container(
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
                            child: Text(messageList[index].message, style: const TextStyle(color: Colors.white))
                          );
                        },
                      ),
                    ),
                  );
                }else{
                  return Container();
                }
              },
            )
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
                        onPressed: () => _sendMessage(_textController.text.trim()), 
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

  void _sendMessage(String message){
    if(message.isEmpty) return;

    _textController.clear();
    _focusNode.requestFocus();

    socketProvider.sendMessage(message);
  }

  @override
  void dispose() {
    _textController.dispose();
    _scrollController.dispose();

    super.dispose();
  }
}