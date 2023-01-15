import 'dart:io' show Platform;

class Environment{
  static String apiUrl    = Platform.isAndroid ? 'http://10.0.2.2:8080' : 'http://localhost:8080';
  static String socketUrl = Platform.isAndroid ? 'ws://10.0.2.2:8080' : 'ws://10.0.2.2:8080';

  static const String registerEndPoint = '/user/create';
  static const String userListEndPoint = '/user/list';

  static const String sendMessageEndPoint = '/ws/message';
}