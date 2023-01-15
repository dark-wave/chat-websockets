import 'dart:convert';

Message messageFromJson(String str) => Message.fromJson(json.decode(str));

String messageToJson(Message data) => json.encode(data.toJson());

class Message {
    Message({
      required this.uidSender,
      required this.uidReceiver,
      required this.message
    });

    String uidSender;
    String uidReceiver;
    String message;

    factory Message.fromJson(Map<String, dynamic> json) => Message(
      uidSender: json["uidSender"],
      uidReceiver: json["uidReceiver"],
      message: json["message"]
    );

    Map<String, dynamic> toJson() => {
      "uidSender": uidSender,
      "uidReceiver": uidReceiver,
      "message": message
    };
}