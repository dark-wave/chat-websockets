import 'dart:convert';

ContactRequest contactRequestFromJson(String str) => ContactRequest.fromJson(json.decode(str));

String contactRequestToJson(ContactRequest data) => json.encode(data.toJson());

class ContactRequest {
    ContactRequest({
        required this.requestUserUuid,
        required this.requestUserName,
        required this.requestUserEmail,
        required this.responseUserEmail,
    });

    String requestUserUuid;
    String requestUserName;
    String requestUserEmail;
    String responseUserEmail;

    factory ContactRequest.fromJson(Map<String, dynamic> json) => ContactRequest(
        requestUserUuid: json["requestUserUuid"],
        requestUserName: json["requestUserName"],
        requestUserEmail: json["requestUserEmail"],
        responseUserEmail: json["responseUserEmail"],
    );

    Map<String, dynamic> toJson() => {
        "requestUserUuid": requestUserUuid,
        "requestUserName": requestUserName,
        "requestUserEmail": requestUserEmail,
        "responseUserEmail": responseUserEmail,
    };
}