import 'dart:convert';

UserLoginResponse userLoginResponseFromJson(String str) => UserLoginResponse.fromJson(json.decode(str));

String userLoginResponseToJson(UserLoginResponse data) => json.encode(data.toJson());

class UserLoginResponse {
    UserLoginResponse({
        required this.uuid,
        required this.name,
        required this.lastname,
        required this.email,
        required this.contacts,
    });

    String uuid;
    String name;
    String lastname;
    String email;
    List<UserLoginResponse> contacts;

    factory UserLoginResponse.fromJson(Map<String, dynamic> json) => UserLoginResponse(
        uuid: json["uuid"] ?? '',
        name: json["name"] ?? '',
        lastname: json["lastname"] ?? '',
        email: json["email"] ?? '',
        contacts: json["contacts"] != null ? List<UserLoginResponse>.from(json["contacts"].map((x) => UserLoginResponse.fromJson(x))) : [],
    );

    Map<String, dynamic> toJson() => {
        "uuid": uuid,
        "name": name,
        "lastname": lastname,
        "email": email,
        "contacts": List<dynamic>.from(contacts.map((x) => x.toJson())),
    };
}