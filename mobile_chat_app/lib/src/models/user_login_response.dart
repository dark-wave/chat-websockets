import 'dart:convert';

UserLoginResponse userLoginResponseFromJson(String str) => UserLoginResponse.fromJson(json.decode(str));

String userLoginResponseToJson(UserLoginResponse data) => json.encode(data.toJson());

class UserLoginResponse {
    UserLoginResponse({
        required this.uuid,
        required this.name,
        required this.lastName,
    });

    String uuid;
    String name;
    String lastName;

    factory UserLoginResponse.fromJson(Map<String, dynamic> json) => UserLoginResponse(
        uuid: json["uuid"],
        name: json["name"],
        lastName: json["lastName"],
    );

    Map<String, dynamic> toJson() => {
        "uuid": uuid,
        "name": name,
        "lastName": lastName,
    };
}
