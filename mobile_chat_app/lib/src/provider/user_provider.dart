import 'package:flutter/material.dart';
import 'package:mobile_chat_app/src/models/user.dart';

class UserProvider extends ChangeNotifier{
  static List<User> _userList = [];
  static User? _user;

  List<User> get userList => _userList;
  User get user => _user!;

  Future<void> register(String name, String lastName, String email, String password) async{
    final registerData = {
      'name': name,
      'lastName': lastName,
      'email': email,
      'password': password
    };

    //TODO: Llamada al servicio de registro
  }
}