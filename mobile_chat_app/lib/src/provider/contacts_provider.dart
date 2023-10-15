import 'package:flutter/material.dart';
import 'package:mobile_chat_app/src/dto/user.dart';

class ContactsProvider with ChangeNotifier{
  List<User> _contactList = [];

  List<User> get contactList => _contactList;

  void addContact(User user){
    int index = _contactList.indexWhere((element) => element.uuid == user.uuid);

    if(index != -1){
      _contactList[index] = user;
    }else{
      _contactList.add(user);
    }

    _contactList.sort((a, b) => a.name.compareTo(b.name));

    notifyListeners();
  }

  void removeContact(User user){
    _contactList.remove(user);

    notifyListeners();
  }
}