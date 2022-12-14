import 'dart:convert';

import 'package:flutter/material.dart';
import 'package:http/http.dart' as http;
import 'package:mobile_chat_app/src/environment/environment.dart';
import 'package:mobile_chat_app/src/models/user.dart';

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

  Future getUserList() async{
    final client = http.Client();

    final clientRespose = await client.get(
      Uri.parse(Environment.apiUrl + Environment.userListEndPoint),
      headers: { 
        'Content-Type':'application/json',
        'Accept': 'application/json'
      }
    );

    if(clientRespose.statusCode == 200){
      List<dynamic> jsonReponse = json.decode(utf8.decode(clientRespose.bodyBytes));
      _userList = jsonReponse.map((user) => User.fromJson(user)).toList();

      notifyListeners();
    }
  }
}