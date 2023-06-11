import 'package:flutter/material.dart';
import 'package:mobile_chat_app/src/provider/user_provider.dart';
import 'package:provider/provider.dart';

class AddContactDialog extends StatefulWidget {
  const AddContactDialog({super.key});

  @override
  State<AddContactDialog> createState() => _AddContactDialogState();
}

class _AddContactDialogState extends State<AddContactDialog> {
  final TextEditingController _emailController = TextEditingController();

  @override
  Widget build(BuildContext context) {
    return Dialog(
      shape: RoundedRectangleBorder(
        borderRadius: BorderRadius.circular(20)
      ),
      child: Container(
        padding: const EdgeInsets.all(20),
        child: Column(
          mainAxisSize: MainAxisSize.min,
          children: [
            const CircleAvatar(
              backgroundImage: AssetImage('assets/images/logo_chat_app_transparente.png'),
              radius: 30.0,
            ),
            const SizedBox(height: 16.0),
            const Text(
              'Agregar contacto',
              style: TextStyle(
                fontSize: 20.0,
                fontWeight: FontWeight.bold,
              )
            ),
            const SizedBox(height: 20),
            TextField(
              controller: _emailController,
              decoration: const InputDecoration(
                border: OutlineInputBorder(),
                labelText: 'Email',
              ),
            ),
            const SizedBox(height: 20),
            Row(
              mainAxisAlignment: MainAxisAlignment.end,
              children: [
                TextButton(
                  onPressed: () => Navigator.pop(context),
                  child: const Text('Cancelar', 
                    style:TextStyle(
                      color: Colors.red,
                      fontSize: 17.0
                    )
                  ),
                ),
                TextButton(
                  onPressed: () => callRequestContactService(context),
                  child: const Text('Agregar',
                    style:TextStyle(
                      fontSize: 17.0
                    )),
                ),
              ],
            )
          ],
        ),
      )
    );
  }
  
  void callRequestContactService(BuildContext context) {
    final String email = _emailController.text;
    if(email.isEmpty) return;

    Provider.of<UserProvider>(context, listen: false).contactRequest(email);
    
    Navigator.pop(context, email);
  }
}