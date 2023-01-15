import 'package:flutter/material.dart';

enum ServerStatus{
  online,
  offline,
  connecting
}

class SocketProvider with ChangeNotifier{
  ServerStatus _serverStatus = ServerStatus.connecting;
}