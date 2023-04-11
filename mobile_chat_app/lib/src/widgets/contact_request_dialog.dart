import 'package:flutter/material.dart';

class ContactRequestDialog extends StatelessWidget {
  final String emailUserRequest;

  const ContactRequestDialog({super.key, required this.emailUserRequest});

  @override
  Widget build(BuildContext context) {
    return AlertDialog(
      shape: RoundedRectangleBorder(
        borderRadius: BorderRadius.circular(20.0),  
      ),
      backgroundColor: Colors.white,
      content: SizedBox(
        height: 200.0,
        child: Column(
          children: <Widget>[
            Container(
              height: 100.0,
              width: 100.0,
              decoration: const BoxDecoration(
                shape: BoxShape.circle,
                color: Colors.green,
              ),
              child: const Icon(
                Icons.person,
                color: Colors.white,
                size: 50.0,
              ),
            ),
            const SizedBox(height: 20.0),
            Text(
              'El usuario $emailUserRequest te ha enviado una solicitud de contacto',
              style: const TextStyle(
                color: Colors.black,
                fontSize: 20.0,
                fontWeight: FontWeight.bold,
              ),
            ),
          ],
        ),
      ),
      actions: [
        TextButton(
          onPressed: () {
            Navigator.of(context).pop();
          },
          child: const Text('Aceptar'),
        ),
        TextButton(
          onPressed: () => Navigator.of(context).pop(),
          child: const Text('Cancelar')
        ) 
      ],
    );
  }
}