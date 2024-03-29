import 'package:flutter/material.dart';
import 'package:mobile_chat_app/src/enum/snackbar_type.dart';
import 'package:mobile_chat_app/src/provider/server_status_provider.dart';
import 'package:mobile_chat_app/src/provider/user_provider.dart';
import 'package:mobile_chat_app/src/widgets/custom_appbar.dart';
import 'package:mobile_chat_app/src/widgets/custom_input.dart';
import 'package:mobile_chat_app/src/widgets/snackbar_info_widget.dart';
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
  void initState() {
    super.initState();
  }

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
    return Scaffold(
      backgroundColor:  const Color(0xffF2F2F2),
      appBar: const CustomAppBar(titulo: 'Register'),
      resizeToAvoidBottomInset: false,
      body: GestureDetector(
        onTap: (){
          FocusScope.of(context).requestFocus(FocusNode()); 
        },
        child: SafeArea(
          child: Padding(
            padding: const EdgeInsets.all(20.0),
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
                  Expanded(child: Container()),
                  Padding(
                    padding: const EdgeInsets.all(20.0),
                    child: Row(
                      children: [
                        Expanded(
                          child: Consumer<ServerStatusProvider>(
                            builder: (context, serverStatusProvider, child){
                              return ElevatedButton.icon(
                                icon: const Icon(Icons.save),
                                onPressed: serverStatusProvider.isServerOnline ? () async => _registerMethod(context) : null ,
                                label: const Text('Register')
                              );
                            },
                          ),
                        ),
                      ],
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
          )
        ),
      ),
    );
  }

  Future<void> _registerMethod(BuildContext context) async{
    final userProvider = Provider.of<UserProvider>(context, listen: false);

    final response = await userProvider.register(
      _nameController.text,
      _lastNameController.text, 
      _emailController.text, 
      _passwordController.text
    );

    //Si el status code es 201 mostramos un mensaje de registro exitoso
    if(response.statusCode == 201){
      Future.delayed(Duration.zero, () {
        SnackbarInfoWidget.showSnackbar(context, SnackbarType.info, 'Usuario registrado con éxito');
      });
    }else if (response.statusCode == 400){
      Future.delayed(Duration.zero, () {
        SnackbarInfoWidget.showSnackbar(context, SnackbarType.warning, 'Usuario ya registrado');
      });
    }else if(response.statusCode == 500){
      Future.delayed(Duration.zero, () {
        SnackbarInfoWidget.showSnackbar(context, SnackbarType.error, 'Error al registrar el usuario');
      });
    }

    //Limpiamos los controladores
    setState(() {
      _nameController.clear();
      _lastNameController.clear();
      _emailController.clear();
      _passwordController.clear();
    });
  }
}