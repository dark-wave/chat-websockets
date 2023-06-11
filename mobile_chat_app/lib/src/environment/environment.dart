import 'dart:io' show Platform;

class Environment{
  // network 1
  //static String apiUrl    = Platform.isAndroid ? 'http://192.168.1.90:3000' : 'http://192.168.1.90:3000';
  //static String socketUrl = Platform.isAndroid ? 'http://192.168.1.90:3000/ws-message' : 'http://192.168.1.90:3000/ws-message';

  // network 2
  static String apiUrl          = Platform.isAndroid ? 'http://10.0.2.2:3000' : 'http://localhost:3000';
  static String socketUrl       = Platform.isAndroid ? 'http://10.0.2.2:3000/ws-message' : 'http://localhost:3000/ws-message';

  static const String testEndpoint            = '/test/';
  static const String registerEndPoint        = '/user/create';
  static const String userListEndPoint        = '/user/list';
  static const String loginEndPoint           = '/login';
  static const String logoutEndPoint          = '/login/logout';
  static const String userContactsEndpoint    = '/user/contacts';
  static const String mesagesEndPoint         = '/message';
  static const String contactRequestEndPoint  = '/contact/request';

  static const String sendMessageEndPoint = '/message';
}