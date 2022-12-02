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
                ElevatedButton.icon(
                  icon: const Icon(Icons.login),
                  onPressed: () => print('Login with form'), 
                  label: const Text('Login')
                ),
                const SizedBox(height: 30), 
                ElevatedButton.icon(
                  icon: const Icon(Icons.login),
                  onPressed: () => print('Login with Google'), 
                  label: const Text('Login with Google')
                ),
                const SizedBox(height: 10),
                ElevatedButton.icon(
                  icon: const Icon(Icons.login),
                  onPressed: () => print('Login with Apple'), 
                  label: const Text('Login with Apple')
                ),
                const SizedBox(height: 10),
                Positioned(
                  bottom: 10,
                  child: InkWell(
                    child: const Text('Aun no tienes cuenta? Registrate aquí'),
                    onTap: () => Navigator.of(context).pushReplacementNamed('register'),
                  ),
                ),
              ],
            ),
          ),
        ),
      ),
    );
  }
}