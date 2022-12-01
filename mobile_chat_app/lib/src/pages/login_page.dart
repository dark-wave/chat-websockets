import 'package:flutter/material.dart';

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
          padding: const EdgeInsets.all(10.0),
          child: Form(
            key: _formKey,
            child: Column(
              mainAxisAlignment: MainAxisAlignment.center,
              children: [
                Expanded(child: Image.asset('assets/images/logo_chat_app.jpg')),
                TextFormField(
                  controller: _userNameController,
                  decoration: const InputDecoration(
                    hintText: 'Username'
                  ),
                ),
                TextFormField(
                  controller: _passwordController,
                  keyboardType: TextInputType.visiblePassword,
                  obscureText: true,
                  decoration: const InputDecoration(
                    hintText: 'Password'
                  ),
                ), 
                ElevatedButton(
                  onPressed: () => print('Login with Google'), 
                  child: const Text('Login with Google')
                ),
                ElevatedButton(
                  onPressed: () => print('Login with Apple'), 
                  child: const Text('Login with Apple')
                ),
                InkWell(
                  child: const Text('Registrate aquí'),
                  onTap: () => Navigator.of(context).pushReplacementNamed('register'),
                ),
                const SizedBox(height: 50)
              ],
            ),
          ),
        ),
      ),
    );
  }
}