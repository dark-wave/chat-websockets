import 'dart:convert';

Message messageFromJson(String str) => Message.fromJson(json.decode(str));

String messageToJson(Message data) => json.encode(data.toJson());

class Message {
    Message({
      required this.uuidSender,
      required this.uuidReceiver,
      required this.message
    });

    String uuidSender;
    String uuidReceiver;
    String message;

    factory Message.fromJson(Map<String, dynamic> json) => Message(
      uuidSender: json["uuidSender"] ?? '',
      uuidReceiver: json["uuidReceiver"] ?? '',
      message: json["message"] ?? ''
    );

    Map<String, dynamic> toJson() => {
      "uuidSender": uuidSender,
      "uuidReceiver": uuidReceiver,
      "message": message
    };
}