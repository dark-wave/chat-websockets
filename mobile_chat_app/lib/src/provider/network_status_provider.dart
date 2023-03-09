import 'package:flutter/material.dart';
import 'package:connectivity_plus/connectivity_plus.dart';

class NetworkStatusProvider extends ChangeNotifier{
  ConnectivityResult _connectivityResult = ConnectivityResult.none;
  final Connectivity _connectivity = Connectivity();
  static bool _isConnected = true;

  bool get isConnected => _isConnected;

  Future<void> checkConnection() async {
    _connectivityResult = await _connectivity.checkConnectivity();
    if(_connectivityResult == ConnectivityResult.none){
      _isConnected = false;
    }else{
      _isConnected = true;
    }
    notifyListeners();
  }
}