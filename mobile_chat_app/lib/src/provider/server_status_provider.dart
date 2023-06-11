import 'dart:async';

import 'package:flutter/material.dart';
import 'package:mobile_chat_app/src/environment/environment.dart';
import 'package:http/http.dart' as http;
import 'package:mobile_chat_app/src/provider/network_status_provider.dart';

class ServerStatusProvider extends ChangeNotifier{
  final NetworkStatusProvider networkStatusProvider;
  bool _isServerOnline = true;
  late Timer _timer;

  bool get isServerOnline => _isServerOnline;

  ServerStatusProvider(this.networkStatusProvider){
    //Chequeamos al inicio para no esperar los 10 segundos
    checkServerStatus();

    //Chequeamos cada 10 segundos el estado del servidor
    _timer = Timer.periodic(const Duration(seconds: 10), (timer) { 
      if(networkStatusProvider.isConnected){
        checkServerStatus();
      }
    });
  }

  Future<void> checkServerStatus() async{
    final url = Uri.parse(Environment.apiUrl + Environment.testEndpoint);

    try{
      final response = await http.get(url);

      if(response.statusCode == 200){
        _isServerOnline = true;
      }else{
        _isServerOnline = false;
      }
    }catch(e){
      _isServerOnline = false;
    }

    notifyListeners();
  }

  @override
  void dispose() {
    _timer.cancel();
    super.dispose();
  }
}