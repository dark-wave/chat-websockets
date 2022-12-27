import 'package:flutter/material.dart';
import 'package:mobile_chat_app/src/provider/user_provider.dart';
import 'package:mobile_chat_app/src/widgets/custom_appbar.dart';
import 'package:mobile_chat_app/src/widgets/custom_input.dart';
import 'package:provider/provider.dart';

class RegisterPage extends StatefulWidget {
  const RegisterPage({super.key});

  @override
  State<RegisterPage> createState() => _RegisterPageState();
}

class _RegisterPageState extends State<RegisterPage> {
  final _formKey = GlobalKey<FormState>();
  final _nameController = TextEditingController();
  final _lastNameController = TextEditingController();
  final _emailController = TextEditingController();
  final _passwordController = TextEditingController();

  @override
  void dispose() {
    _nameController.dispose();
    _lastNameController.dispose();
    _emailController.dispose();
    _passwordController.dispose();

    super.dispose();
  }

  @override
  Widget build(BuildContext context) {
    final userService = Provider.of<UserProvider>(context);

    return Scaffold(
      backgroundColor:  const Color(0xffF2F2F2),
      appBar: const CustomAppBar(titulo: 'Register'),
      body: SafeArea(
        child: Padding(
          padding: const EdgeInsets.all(20.0),
          child: SingleChildScrollView(
            child: Form(
              key: _formKey,
              child: Column(
                children: [
                  CustomInput(
                    icon: Icons.perm_identity, 
                    hintText: 'name', 
                    textController: _nameController
                  ),
                  CustomInput(
                    icon: Icons.perm_identity, 
                    hintText: 'last name', 
                    textController: _lastNameController
                  ),
                  CustomInput(
                    icon: Icons.email, 
                    hintText: 'email', 
                    textController: _emailController,
                    keyboardType: TextInputType.emailAddress,
                  ),
                  CustomInput(
                    icon: Icons.password, 
                    hintText: 'password', 
                    textController: _passwordController,
                    isPassword: true,
                  ),
                  Padding(
                    padding: const EdgeInsets.all(20.0),
                    child: ElevatedButton.icon(
                      icon: const Icon(Icons.save),
                      onPressed: () async {
                        final response = await userService.register(
                        _nameController.text,
                        _lastNameController.text, 
                        _emailController.text, 
                        _passwordController.text
                        );
                      },
                      label: const Text('Register')
                    ),
                  ),
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
              ),
            ),
          ),
        )
      ),
    );
  }
}