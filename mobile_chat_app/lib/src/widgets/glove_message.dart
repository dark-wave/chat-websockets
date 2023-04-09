import 'package:flutter/material.dart';

class GloveMessage extends StatelessWidget {
  final String message;
  final bool isMe;

  const GloveMessage({super.key,required this.message, required this.isMe});

  @override
  Widget build(BuildContext context) {
    return isMe ? Container(
      padding: const EdgeInsets.all(8),
      margin: const EdgeInsets.only(
        right: 5,
        bottom: 5,
        left: 100
      ),
      decoration: BoxDecoration(
        color: const Color(0xff4D9EF6),
        borderRadius: BorderRadius.circular(20)
      ),
      child: Text(message, style: const TextStyle(color: Colors.white))
    ) : Container(
      padding: const EdgeInsets.all(8),
      margin: const EdgeInsets.only(
        right: 100,
        bottom: 5,
        left: 5
      ),
      decoration: BoxDecoration(
        color: Colors.grey[300],
        borderRadius: BorderRadius.circular(20)
      ),
      child: Text(message, style: const TextStyle(color: Colors.black))
    );
  }
}