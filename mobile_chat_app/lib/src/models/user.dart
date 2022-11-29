import 'dart:convert';

User userFromJson(String str) => User.fromJson(json.decode(str));

String userToJson(User data) => json.encode(data.toJson());

class User {
    User({
        required this.name,
        required this.email,
        required this.password,
        required this.uid,
        required this.online,
    });

    String name;
    String email;
    String password;
    String uid;
    bool online;

    factory User.fromJson(Map<String, dynamic> json) => User(
      name: json["name"],
      email: json["email"],
      password: json["password"],
      uid: json["uid"],
      online: json["online"],
    );

    Map<String, dynamic> toJson() => {
      "name": name,
      "email": email,
      "password": password,
      "uid": uid,
      "online": online,
    };
}
