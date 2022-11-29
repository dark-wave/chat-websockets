

import 'package:flutter/cupertino.dart';
import 'package:mobile_chat_app/src/models/user.dart';

class UserProvider extends ChangeNotifier{
  static List<User> _userList = [];
  static User? _user;

  List<User> get userList => _userList;
  User get user => _user!;
}