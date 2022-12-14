import 'package:flutter/material.dart';
import 'package:mobile_chat_app/src/widgets/custom_input.dart';

class LoginPage extends StatefulWidget {
  const LoginPage({super.key});

  @override
  State<LoginPage> createState() => _LoginPageState();
}

class _LoginPageState extends State<LoginPage> {
  final _formKey = GlobalKey<FormState>();
  final _userNameController = TextEditingController();
  final _passwordController = TextEditingController();

  @override
  void dispose() {
    _userNameController.dispose();
    _passwordController.dispose();

    super.dispose();
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      body: SafeArea(
        child: Padding(
          padding: const EdgeInsets.all(20.0),
          child: Form(
            key: _formKey,
            child: Column(
              mainAxisAlignment: MainAxisAlignment.center,
              children: [
                Expanded(child: Image.asset('assets/images/logo_chat_app_transparente.png')),
                CustomInput(
                  icon: Icons.email, 
                  hintText: 'Email', 
                  textController: _userNameController
                ),
                CustomInput(
                  icon: Icons.password, 
                  hintText: 'Password', 
                  textController: _passwordController,
                  isPassword: true,
                ),
                const SizedBox(height: 20),
                Row(
                  children: [
                    Expanded(
                      child: ElevatedButton.icon(
                        icon: const Icon(Icons.login),
                        onPressed: () => Navigator.of(context).pushReplacementNamed('contacts'), 
                        label: const Text('Login')
                      ),
                    ),
                  ],
                ),
                const SizedBox(height: 20),
                Row(
                  mainAxisAlignment: MainAxisAlignment.center,
                  children: [
                    const Text('A??n no tienes cuenta? Reg??strate '),
                    InkWell(
                      child: const Text(
                        'aqu??',
                        style: TextStyle(
                          color: Colors.blue,
                          decoration: TextDecoration.underline
                        )  
                      ),
                      onTap: () => Navigator.pushReplacementNamed(context, 'register'),
                    )
                  ],
                )
              ],
            ),
          ),
        ),
      ),
    );
  }
}