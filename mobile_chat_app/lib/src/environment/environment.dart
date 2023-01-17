import 'dart:io' show Platform;

class Environment{
  static String apiUrl    = Platform.isAndroid ? 'http://10.0.2.2:3000' : 'http://localhost:3000';
  static String socketUrl = Platform.isAndroid ? 'http://10.0.2.2:3000/websocket-chat' : 'http://localhost:3000/websocket-chat';

  static const String registerEndPoint = '/user/create';
  static const String userListEndPoint = '/user/list';

  static const String sendMessageEndPoint = '/message';
}