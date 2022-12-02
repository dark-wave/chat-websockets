import 'package:flutter/material.dart';
import 'package:mobile_chat_app/src/pages/chat_page.dart';
import 'package:mobile_chat_app/src/pages/contacts_page.dart';
import 'package:mobile_chat_app/src/pages/login_page.dart';
import 'package:mobile_chat_app/src/pages/register_page.dart';
import 'package:mobile_chat_app/src/provider/message_provider.dart';
import 'package:mobile_chat_app/src/provider/user_provider.dart';
import 'package:provider/provider.dart';

void main() => runApp(const ChatApp());

class ChatApp extends StatelessWidget {
  const ChatApp({super.key});

  @override
  Widget build(BuildContext context) {
    return MultiProvider(
      providers: [
        ChangeNotifierProvider(create: (_) => UserProvider()),
        ChangeNotifierProvider(create: (_) => MessageProvider())
      ],
      child: MaterialApp(
        title: 'Chat App',
        debugShowCheckedModeBanner: false,
        theme: ThemeData.light().copyWith(
          primaryColor: Colors.purpleAccent,
          inputDecorationTheme: InputDecorationTheme(
            focusedBorder: const OutlineInputBorder(
              borderSide: BorderSide(color: Colors.white)
            ),
            border: const OutlineInputBorder(
              borderSide: BorderSide(color: Colors.blue)
            ),
            hintStyle: TextStyle(color: Colors.white.withAlpha(80)),
          ),
          elevatedButtonTheme: ElevatedButtonThemeData(
            style: ElevatedButton.styleFrom(
              backgroundColor: Colors.deepPurpleAccent,
              shape: const BeveledRectangleBorder(borderRadius: BorderRadius.all(Radius.circular(5))),
              textStyle: const TextStyle(
                color: Colors.white70,
                fontSize: 20.0
              )
            )
          )
        ),
        initialRoute: 'login',
        routes: {
          'login'     : (BuildContext context) => const LoginPage(),
          'register'  : (BuildContext context) => const RegisterPage(),
          'contacts'  : (BuildContext context) => const ContactsPage(),
          'chat'      : (BuildContext context) => const ChatPage()
        }
      ),
    );
  }
}