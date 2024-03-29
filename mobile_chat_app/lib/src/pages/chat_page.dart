import 'package:flutter/material.dart';
import 'package:flutter/scheduler.dart';
import 'package:mobile_chat_app/src/dto/message.dart';
import 'package:mobile_chat_app/src/dto/user.dart';
import 'package:mobile_chat_app/src/provider/login_provider.dart';
import 'package:mobile_chat_app/src/provider/socket_provider.dart';
import 'package:mobile_chat_app/src/widgets/glove_message.dart';
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
  late LoginProvider loginProvider;

  @override
  void initState() {
    super.initState();
    
    socketProvider = Provider.of<SocketProvider>(context, listen: false);
    loginProvider = Provider.of<LoginProvider>(context, listen: false);
  }

  @override
  Widget build(BuildContext context) {
    User? user = ModalRoute.of(context)!.settings.arguments as User?;

    return Scaffold(
      appBar: AppBar(
        centerTitle: true,
        elevation: 1,
        leading: GestureDetector(
          onTap: (){
            Navigator.pushReplacementNamed(context, 'contacts');
          },
          child: const Icon(Icons.arrow_back_ios, size: 25, color: Colors.white)
        ),
        title: Column(      
          children: [
            CircleAvatar(
              backgroundColor: Colors.blue[100],
              maxRadius: 14,
              child: Text(user!.name.substring(0,2), style: const TextStyle(fontSize: 15)),
            ),
            const SizedBox(height: 3),
            Text(user.name, style: const TextStyle(color: Colors.white, fontSize: 15)),
          ],
        ),
      ),
      body: Column(
        children: [
          Expanded(
            child: Consumer<SocketProvider>(
              builder: (context, data, child){
                var messageList = data.messageList.where((message) => (message.uuidSender == loginProvider.userLoginResponse.uuid && message.uuidReceiver == user.uuid) || (message.uuidSender == user.uuid && message.uuidReceiver == loginProvider.userLoginResponse.uuid)).toList();

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
                        itemBuilder: (context, index){
                          return GloveMessage(message: messageList[index].message, isMe: messageList[index].uuidSender == loginProvider.userLoginResponse.uuid);
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
                        onPressed: () => _sendMessage(user, _textController.text.trim()), 
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

  void _sendMessage(User user, String messageStr){
    if(messageStr.isEmpty) return;

    Message message = Message(
      uuidSender: loginProvider.userLoginResponse.uuid, 
      uuidReceiver: user.uuid, 
      message: messageStr
    );

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