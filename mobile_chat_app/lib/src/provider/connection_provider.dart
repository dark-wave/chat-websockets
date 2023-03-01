import 'package:connectivity_plus/connectivity_plus.dart';
import 'package:flutter/material.dart';

class ConnectionProvider extends ChangeNotifier {
  ConnectivityResult _connectionStatus = ConnectivityResult.none;

  ConnectivityResult get connectionStatus => _connectionStatus;

  Future<void> init() async{
    final Connectivity connectivity = Connectivity();

    final status = await connectivity.checkConnectivity();
    _updateConnectionStatus(status);

    connectivity.onConnectivityChanged.listen(_updateConnectionStatus);
  }

  void _updateConnectionStatus(ConnectivityResult status) {
    _connectionStatus = status;
    notifyListeners();
  }
}