import 'dart:convert';
import 'package:flutter/material.dart';
import 'package:http/http.dart' as http;
import 'package:mobile_chat_app/src/environment/environment.dart';
import 'package:mobile_chat_app/src/models/user_login_response.dart';

class LoginProvider extends ChangeNotifier{
  static UserLoginResponse? _userLoginResponse;

  UserLoginResponse get userLoginResponse => _userLoginResponse!;
  
  Future<bool> login(String username, String password) async{  
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
      if(loginServiceResponse.body.isNotEmpty){
        _userLoginResponse = userLoginResponseFromJson(loginServiceResponse.body);  
      }
      
      return true;
    }else{
      //TODO: Tratamiento de mensaje de error.
      return false;
    }
  }
}