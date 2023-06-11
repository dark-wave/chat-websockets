import 'package:flutter/material.dart';

class ServerMessageWidget extends StatelessWidget {
  final bool isError;
  final String message;

  const ServerMessageWidget({super.key, required this.message, required this.isError});

  @override
  Widget build(BuildContext context) {
    return AlertDialog(
      title: isError ? const CircleAvatar(
        backgroundColor: Colors.red,
        child: Icon(Icons.error_outline),
      ) : const CircleAvatar(
        backgroundColor: Colors.green,
        child: Icon(Icons.info),
      ),
      content: Text(message),
      actions: [
        ElevatedButton(
          onPressed: () => Navigator.of(context).pop(),
          child: const Text('Aceptar'),
        ),
      ],
    );
  }
}