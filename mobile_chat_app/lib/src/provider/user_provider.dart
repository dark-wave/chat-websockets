import 'dart:convert';

import 'package:flutter/material.dart';
import 'package:http/http.dart' as http;
import 'package:mobile_chat_app/src/environment/environment.dart';
import 'package:mobile_chat_app/src/models/user.dart';
import 'package:mobile_chat_app/src/utils/crypto_utils.dart';

class UserProvider extends ChangeNotifier{
  static List<User> _userList = [];
  static User? _user;

  List<User> get userList => _userList;
  User get user => _user!;

  Future register(String name, String lastName, String email, String password) async{
    final client = http.Client();

    final registerData = {
      'name': name,
      'lastName': lastName,
      'email': email,
      'password': password
    };

    print(CrytoUtils.encryptString(jsonEncode(registerData)));

    final serviceResponse = await client.post(
      Uri.parse(Environment.apiUrl + Environment.registerEndPoint),
      body: jsonEncode(registerData),
      headers: { 
        'Content-Type':'application/json',
        'Accept': 'application/json'
      }
    );

    return serviceResponse;
  }
}