import 'package:flutter/material.dart';
import 'package:mobile_chat_app/src/pages/login_page.dart';
import 'package:mobile_chat_app/src/pages/register_page.dart';
import 'package:mobile_chat_app/src/provider/user_provider.dart';
import 'package:provider/provider.dart';

void main() => runApp(const ChatApp());

class ChatApp extends StatelessWidget {
  const ChatApp({super.key});

  @override
  Widget build(BuildContext context) {
    return MultiProvider(
      providers: [
        ChangeNotifierProvider(create: (_) => UserProvider())
      ],
      child: MaterialApp(
        title: 'Chat App',
        initialRoute: 'login',
        routes: {
          'login'     : (BuildContext context) => const LoginPage(),
          'register'  : (BuildContext context) => const RegisterPage()
        }
      ),
    );
  }
}