import 'package:flutter/material.dart';

class RegisterPage extends StatefulWidget {
  const RegisterPage({super.key});

  @override
  State<RegisterPage> createState() => _RegisterPageState();
}

class _RegisterPageState extends State<RegisterPage> {

  @override
  Widget build(BuildContext context) {
    final Size size = MediaQuery.of(context).size;

    return Scaffold(
      backgroundColor:  const Color(0xffF2F2F2),
      body: SafeArea(
        child: SingleChildScrollView(
          physics: const BouncingScrollPhysics(),
          child: SizedBox(
            height: size.height * 0.9,
            child: Column(
              children: [
                Row(
                  mainAxisAlignment: MainAxisAlignment.center,
                  children: [
                    const Text('Ya tienes cuenta? Ve a la '),
                    InkWell(
                      child: const Text(
                        'página de login',
                        style: TextStyle(
                          color: Colors.blue,
                          decoration: TextDecoration.underline
                        )
                      ),
                      onTap: () => Navigator.pushReplacementNamed(context, 'login'),
                    )
                  ],
                )
              ],
            )
          ),
        )
      ),
    );
  }
}