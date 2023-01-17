import 'package:flutter/material.dart';
import 'package:mobile_chat_app/src/environment/environment.dart';
import 'package:socket_io_client/socket_io_client.dart' as IO;

enum ServerStatus{
  online,
  offline,
  connecting
}

class SocketProvider with ChangeNotifier{
  late IO.Socket _socket;

  IO.Socket get socket => _socket;
  Function get emit => _socket.emit;

  //Conexión para la comunicación con sockets
  void connect() async{
    _socket = IO.io(Environment.socketUrl, {
      'transports': ['websocket'],
      'autoConnect': true,
      'forceNew': true
    });

    _socket.on('connect', (_) {
      print('Servidor contectado');
      notifyListeners();
    });

    _socket.onConnectTimeout((data) => print(data));
    _socket.onConnectError((data) => print(data));

    _socket.on('disconnect', (_) {
      print('Servidor desconectado');
      notifyListeners();
    });
  }

  // Desconexion de la comunicación por sockets
  void disconect(){
    _socket.disconnect();
  }
}