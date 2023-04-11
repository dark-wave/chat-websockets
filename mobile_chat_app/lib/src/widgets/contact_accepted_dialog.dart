import 'package:flutter/material.dart';

class ContactAcceptedDialog extends StatelessWidget {
  const ContactAcceptedDialog({super.key});

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
                Icons.check,
                color: Colors.white,
                size: 50.0,
              ),
            ),
            const SizedBox(height: 20.0),
            const Text(
              'El usuario te ha aceptado como contacto',
              style: TextStyle(
                color: Colors.black,
                fontSize: 20.0,
                fontWeight: FontWeight.bold,
              ),
            ),
          ],
        ),
      ),
    );
  }
}