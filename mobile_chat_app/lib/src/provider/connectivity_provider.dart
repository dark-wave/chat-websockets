import 'package:connectivity_plus/connectivity_plus.dart';
import 'package:flutter/material.dart';

class ConnectivityProvider extends ChangeNotifier{
  late ConnectivityResult _connectivityResult;

  ConnectivityResult get connectivityResult => _connectivityResult;

  ConnectivityProvider() {
    Connectivity().onConnectivityChanged.listen((ConnectivityResult result) {
      _connectivityResult = result;
      notifyListeners();
    });
  }
}