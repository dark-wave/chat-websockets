import 'dart:convert';
import 'package:flutter/material.dart';
import 'package:http/http.dart' as http;
import 'package:mobile_chat_app/src/environment/environment.dart';
import 'package:mobile_chat_app/src/models/login_response.dart';

class LoginProvider extends ChangeNotifier{
  
  Future login(String username, String password) async{  
    final client = http.Client();

    final loginData = {
      'email': username,
      'password': password
    };

    final loginServiceResponse = await client.post(
      Uri.parse(Environment.apiUrl + Environment.loginEndPoint),
      body: jsonEncode(loginData),
      headers: { 
        'Content-Type':'application/json',
        'Accept': 'application/json'
      }
    );

    if(loginServiceResponse.statusCode == 200){
      
    }else{
      //TODO: Tratamiento de mensaje de error.
    }
  }
}