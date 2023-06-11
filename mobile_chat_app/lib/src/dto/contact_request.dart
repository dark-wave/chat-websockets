import 'dart:convert';

ContactRequest contactRequestFromJson(String str) => ContactRequest.fromJson(json.decode(str));

String contactRequestToJson(ContactRequest data) => json.encode(data.toJson());

class ContactRequest {
    ContactRequest({
        required this.requestUserUuid,
        required this.userEmail
    });

    String requestUserUuid;
    String userEmail;

    factory ContactRequest.fromJson(Map<String, dynamic> json) => ContactRequest(
        requestUserUuid: json["requestUserUuid"],
        userEmail: json["userEmail"]
    );

    Map<String, dynamic> toJson() => {
        "requestUserUuid": requestUserUuid,
        "userEmail": userEmail,
    };
}