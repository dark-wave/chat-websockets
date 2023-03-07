import 'dart:async';

import 'package:flutter/material.dart';
import 'package:mobile_chat_app/src/environment/environment.dart';
import 'package:http/http.dart' as http;

class ServerStatusProvider extends ChangeNotifier{
  bool _isServerOnline = true;
  late Timer _timer;

  bool get isServerOnline => _isServerOnline;

  ServerStatusProvider(){
    _timer = Timer.periodic(const Duration(seconds: 10), (timer) { 
      checkServerStatus();
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