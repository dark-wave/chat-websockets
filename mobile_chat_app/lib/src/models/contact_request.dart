import 'dart:convert';

ContactRequest contactRequestFromJson(String str) => ContactRequest.fromJson(json.decode(str));

String contactRequestToJson(ContactRequest data) => json.encode(data.toJson());

class ContactRequest {
    ContactRequest({
        required this.userUuid,
        required this.contactEmail,
    });

    String userUuid;
    String contactEmail;

    factory ContactRequest.fromJson(Map<String, dynamic> json) => ContactRequest(
        userUuid: json["userUuid"],
        contactEmail: json["contactEmail"],
    );

    Map<String, dynamic> toJson() => {
        "userUuid": userUuid,
        "contactEmail": contactEmail,
    };
}
