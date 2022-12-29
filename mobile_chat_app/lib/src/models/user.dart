import 'dart:convert';

User userFromJson(String str) => User.fromJson(json.decode(str));

String userToJson(User data) => json.encode(data.toJson());

class User {
    User({
      required this.name,
      required this.email,
      required this.password,
      required this.uuid,
      required this.online,
    });

    String name;
    String email;
    String password;
    String uuid;
    bool online;

    factory User.fromJson(Map<String, dynamic> json) => User(
      name: json["name"],
      email: json["email"],
      password: json["password"],
      uuid: json["uuid"],
      online: json["online"] ?? false,
    );

    Map<String, dynamic> toJson() => {
      "name": name,
      "email": email,
      "password": password,
      "uuid": uuid,
      "online": online,
    };
}
