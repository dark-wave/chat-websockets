import 'dart:convert';

import 'package:mobile_chat_app/src/models/contact_request.dart';

ContactResponse contactResponseFromJson(String str) => ContactResponse.fromJson(json.decode(str));

String contactResponseToJson(ContactResponse data) => json.encode(data.toJson());

class ContactResponse {
    ContactResponse({
        required this.userUuidResponse,
        required this.contactAccepted,
        required this.contactRequest,
    });

    String userUuidResponse;
    bool contactAccepted;
    ContactRequest contactRequest;

    factory ContactResponse.fromJson(Map<String, dynamic> json) => ContactResponse(
        userUuidResponse: json["userUuidResponse"],
        contactAccepted: json["contactAccepted"],
        contactRequest: ContactRequest.fromJson(json["contactRequest"]),
    );

    Map<String, dynamic> toJson() => {
        "userUuidResponse": userUuidResponse,
        "contactAccepted": contactAccepted,
        "contactRequest": contactRequest.toJson(),
    };
}