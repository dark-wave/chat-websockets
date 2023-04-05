import 'dart:convert';

User userFromJson(String str) => User.fromJson(json.decode(str));

String userToJson(User data) => json.encode(data.toJson());

class User {
    User({
      required this.name,
      required this.uuid,
      required this.online,
    });

    String name;
    String uuid;
    bool online;

    factory User.fromJson(Map<String, dynamic> json) => User(
      name: json["name"],
      uuid: json["uuid"],
      online: json["online"] ?? false,
    );

    Map<String, dynamic> toJson() => {
      "name": name,
      "uuid": uuid,
      "online": online,
    };
}
