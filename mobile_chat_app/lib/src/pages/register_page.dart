import 'package:flutter/material.dart';
import 'package:mobile_chat_app/src/widgets/custom_appbar.dart';

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
    final Size size = MediaQuery.of(context).size;

    return Scaffold(
      backgroundColor:  const Color(0xffF2F2F2),
      appBar: const CustomAppBar(titulo: 'Register'),
      body: SafeArea(
        child: Padding(
          padding: const EdgeInsets.all(20.0),
          child: SizedBox(
            height: size.height * 0.9,
            child: Column(
              children: [
                Expanded(
                  child: Form(
                    key: _formKey,
                    child: Column(
                      children: [
                        TextFormField(
                          controller: _nameController,
                          keyboardType: TextInputType.name,
                          decoration: const InputDecoration(
                            hintText: 'Name'
                          ),
                        ),
                        TextFormField(
                          controller: _lastNameController,
                          keyboardType: TextInputType.name,
                          decoration: const InputDecoration(
                            hintText: 'Last Name'
                          ),
                        ),
                        TextFormField(
                          controller: _emailController,
                          keyboardType: TextInputType.emailAddress,
                          decoration: const InputDecoration(
                            hintText: 'Email'
                          ),
                        ),
                        TextFormField(
                          controller: _passwordController,
                          keyboardType: TextInputType.visiblePassword,
                          decoration: const InputDecoration(
                            hintText: 'Password'
                          ),
                        ),
                        Expanded(child: Container()),
                        Padding(
                          padding: const EdgeInsets.all(20.0),
                          child: ElevatedButton.icon(
                            icon: const Icon(Icons.save),
                            onPressed: () => print('Register page'), 
                            label: const Text('Register')
                          ),
                        ),
                      ],
                    ),
                  )
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
            )
          ),
        )
      ),
    );
  }
}