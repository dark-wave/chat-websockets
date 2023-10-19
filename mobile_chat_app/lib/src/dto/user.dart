import 'dart:convert';

User userFromJson(String str) => User.fromJson(json.decode(str));

String userToJson(User data) => json.encode(data.toJson());

class User implements Comparable<User>{
    User({
      required this.name,
      required this.uuid,
      required this.connected,
    });

    String name;
    String uuid;
    bool connected;

    factory User.fromJson(Map<String, dynamic> json) => User(
      name: json["name"],
      uuid: json["uuid"],
      connected: json["connected"] ?? false,
    );

    Map<String, dynamic> toJson() => {
      "name": name,
      "uuid": uuid,
      "connected": connected,
    };

    @override
    int compareTo(User other) {
      return name.compareTo(other.name);
    }
}
