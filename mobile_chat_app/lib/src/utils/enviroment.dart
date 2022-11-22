import 'dart:io';

class Environment{
  static final String apiUrl = Platform.isAndroid ? '' : '';
  static final String apiSocket = Platform.isAndroid ? '' : '';
}