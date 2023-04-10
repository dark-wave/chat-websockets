import 'package:flutter/material.dart';
import 'package:mobile_chat_app/src/pages/chat_page.dart';
import 'package:mobile_chat_app/src/pages/contacts_page.dart';
import 'package:mobile_chat_app/src/pages/login_page.dart';
import 'package:mobile_chat_app/src/pages/register_page.dart';
import 'package:mobile_chat_app/src/provider/event_listener_provider.dart';
import 'package:mobile_chat_app/src/provider/login_provider.dart';
import 'package:mobile_chat_app/src/provider/message_provider.dart';
import 'package:mobile_chat_app/src/provider/network_status_provider.dart';
import 'package:mobile_chat_app/src/provider/server_status_provider.dart';
import 'package:mobile_chat_app/src/provider/socket_provider.dart';
import 'package:mobile_chat_app/src/provider/user_provider.dart';
import 'package:provider/provider.dart';

void main() => runApp(const ChatApp());

class ChatApp extends StatelessWidget {
  const ChatApp({super.key});

  @override
  Widget build(BuildContext context) {
    return MultiProvider(
      providers: [
        ChangeNotifierProvider(create: (_) => LoginProvider()),
        ChangeNotifierProvider(create: (BuildContext context) => UserProvider(context)),
        ChangeNotifierProvider(create: (_) => MessageProvider()),
        ChangeNotifierProvider(create: (_) => SocketProvider()),
        ChangeNotifierProvider(create: (_) => EventListenerProvider()),
        ChangeNotifierProvider(create: (_) => NetworkStatusProvider()),
        ChangeNotifierProvider(create: (context) => ServerStatusProvider(Provider.of<NetworkStatusProvider>(context, listen: false)))
      ],
      child: MaterialApp(
        title: 'Chat App',
        debugShowCheckedModeBanner: false,
        theme: ThemeData.light().copyWith(
          primaryColor: Colors.deepPurpleAccent,
          appBarTheme: const AppBarTheme(
            elevation: 0.0,
            backgroundColor: Colors.deepPurpleAccent,
            centerTitle: true,
            titleTextStyle: TextStyle(
              color: Colors.white,
              fontSize: 25.0,
              fontWeight: FontWeight.bold
            )
          ),
          inputDecorationTheme: const InputDecorationTheme(
            hintStyle: TextStyle(color: Colors.black),
          ),
          elevatedButtonTheme: ElevatedButtonThemeData(
            style: ElevatedButton.styleFrom(
              backgroundColor: Colors.deepPurpleAccent,
              shape: const BeveledRectangleBorder(borderRadius: BorderRadius.all(Radius.circular(5))),
              textStyle: const TextStyle(
                color: Colors.white70,
                fontSize: 20.0,
                fontWeight: FontWeight.bold
              ),
            )
          ),
          snackBarTheme: SnackBarThemeData(
            backgroundColor: Colors.redAccent.withOpacity(0.8),            
            contentTextStyle: const TextStyle(
              color: Colors.white,
              fontSize: 20.0,
              fontWeight: FontWeight.bold
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