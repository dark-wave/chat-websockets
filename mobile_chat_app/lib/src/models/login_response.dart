import 'dart:convert';
import 'package:mobile_chat_app/src/models/user.dart';

LoginResponse loginResponseFromJson(String str) => LoginResponse.fromJson(json.decode(str));

String loginResponseToJson(LoginResponse data) => json.encode(data.toJson());

class LoginResponse {
    LoginResponse({
        required this.loginOk,
        required this.user,
        required this.token,
    });

    bool loginOk;
    User user;
    String token;

    factory LoginResponse.fromJson(Map<String, dynamic> json) => LoginResponse(
        loginOk: json["loginOk"],
        user: User.fromJson(json["user"]),
        token: json["token"],
    );

    Map<String, dynamic> toJson() => {
        "loginOk": loginOk,
        "user": user.toJson(),
        "token": token,
    };
}
