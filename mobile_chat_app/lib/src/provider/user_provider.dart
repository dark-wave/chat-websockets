import 'dart:convert';

import 'package:flutter/material.dart';
import 'package:http/http.dart' as http;
import 'package:mobile_chat_app/src/environment/environment.dart';
import 'package:mobile_chat_app/src/models/user.dart';
import 'package:mobile_chat_app/src/provider/login_provider.dart';
import 'package:provider/provider.dart';

class UserProvider extends ChangeNotifier{
  LoginProvider? loginProvider;
  static List<User> _userList = [];
  static User? _user;

  List<User> get userList => _userList;
  User get user => _user!;


  UserProvider(BuildContext context){
    loginProvider = Provider.of<LoginProvider>(context, listen: false);
  }



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

  Future getContacts() async{
    final client = http.Client();
    String? useruuid = loginProvider?.userLoginResponse.uuid;

    final clientRespose = await client.get(
      Uri.parse('${Environment.apiUrl}${Environment.userContactsEndpoint}/$useruuid'),
      headers: { 
        'Content-Type':'application/json',
        'Accept': 'application/json'
      }
    );

    if (clientRespose.statusCode == 200){
      List<dynamic> jsonReponse = json.decode(utf8.decode(clientRespose.bodyBytes));
      _userList = jsonReponse.map((user) => User.fromJson(user)).toList();

      notifyListeners();
    }
  }

  Future contactRequest(String contactEmail) async{
    final client = http.Client();
    String? useruuid = loginProvider?.userLoginResponse.uuid;

    final contactRequestData = {
      'userUuid': useruuid,
      'contactEmail': contactEmail
    };

    final clientRespose = await client.post(
      Uri.parse('${Environment.apiUrl}${Environment.contactRequestEndPoint}'),
      headers: { 
        'Content-Type':'application/json',
        'Accept': 'application/json'
      },
      body: jsonEncode(contactRequestData)
    );

    return clientRespose;
  }
}