import 'dart:io' show Platform;

class Environment{
  /*
  static String apiUrl    = Platform.isAndroid ? 'http://10.0.2.2:3000' : 'http://localhost:3000';
  static String socketUrl = Platform.isAndroid ? 'http://10.0.2.2:3000/ws-message' : 'http://localhost:3000/ws-message';
  */

  static String apiUrl    = Platform.isAndroid ? 'http://192.168.1.90:3000' : 'http://192.168.1.90:3000';
  static String socketUrl = Platform.isAndroid ? 'http://192.168.1.90:3000/ws-message' : 'http://192.168.1.90:3000/ws-message';

  static const String registerEndPoint = '/user/create';
  static const String userListEndPoint = '/user/list';

  static const String sendMessageEndPoint = '/message';
}