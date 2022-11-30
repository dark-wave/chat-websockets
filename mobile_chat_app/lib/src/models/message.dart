import 'dart:convert';

Message messageFromJson(String str) => Message.fromJson(json.decode(str));

String messageToJson(Message data) => json.encode(data.toJson());

class Message {
    Message({
      required this.uidSender,
      required this.uidReceiver,
      required this.message,
      required this.creationDate,
      required this.read,
    });

    String uidSender;
    String uidReceiver;
    String message;
    String creationDate;
    bool read;

    factory Message.fromJson(Map<String, dynamic> json) => Message(
      uidSender: json["uidSender"],
      uidReceiver: json["uidReceiver"],
      message: json["message"],
      creationDate: json["creationDate"],
      read: json["read"],
    );

    Map<String, dynamic> toJson() => {
      "uidSender": uidSender,
      "uidReceiver": uidReceiver,
      "message": message,
      "creationDate": creationDate,
      "read": read,
    };
}