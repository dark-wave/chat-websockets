import 'dart:convert';
import 'message.dart';

MessagesResponse messagesResponseFromJson(String str) => MessagesResponse.fromJson(json.decode(str));

String messagesResponseToJson(MessagesResponse data) => json.encode(data.toJson());

class MessagesResponse {
    MessagesResponse({
      required this.ok,
      required this.message,
    });

    bool ok;
    Message message;

    factory MessagesResponse.fromJson(Map<String, dynamic> json) => MessagesResponse(
        ok: json["ok"],
        message: Message.fromJson(json["message"]),
    );

    Map<String, dynamic> toJson() => {
        "ok": ok,
        "message": message.toJson(),
    };
}