import 'package:flutter/material.dart';

class CustomInput extends StatelessWidget {

  final IconData icon;
  final String hintText;
  final TextEditingController textController;
  final TextInputType keyboardType;
  final bool isPassword;


  const CustomInput({
    super.key, 
    required this.icon, 
    required this.hintText, 
    required this.textController,
    this.keyboardType = TextInputType.text, 
    this.isPassword = false
  });

  @override
  Widget build(BuildContext context) {
    return Container(
      padding: const EdgeInsets.only( top: 5, left: 5, bottom: 5, right: 20 ),
      margin: const EdgeInsets.only( bottom: 20 ),
      decoration: BoxDecoration(
        color: Colors.white,
        borderRadius: BorderRadius.circular(30),
        boxShadow: <BoxShadow>[
          BoxShadow(
            color: Colors.black.withOpacity(0.05),
            offset: const Offset(0, 5),
            blurRadius: 5
          )
        ]
      ),
      child: TextFormField(
        controller: textController,
        autocorrect: false,
        keyboardType: keyboardType,
        obscureText: isPassword,
        decoration: InputDecoration(
          prefixIcon: Icon(icon),
          focusedBorder: InputBorder.none,
          border: InputBorder.none,
          hintText: hintText
        ),
      ),
    );
  }
}