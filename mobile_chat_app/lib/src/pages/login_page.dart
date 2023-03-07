import 'package:flutter/material.dart';
import 'package:mobile_chat_app/src/provider/login_provider.dart';
import 'package:mobile_chat_app/src/provider/server_status_provider.dart';
import 'package:mobile_chat_app/src/provider/socket_provider.dart';
import 'package:mobile_chat_app/src/widgets/custom_input.dart';
import 'package:mobile_chat_app/src/widgets/network_status_widget.dart';
import 'package:provider/provider.dart';

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
                const NetworkStatusWidget(),
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
                      child: Consumer<ServerStatusProvider>(
                       builder: (context, serverStatusProvider, child){
                        return ElevatedButton.icon(
                          icon: const Icon(Icons.login),
                          onPressed: serverStatusProvider.isServerOnline ? _loginMethod : null , 
                          label: const Text('Login')
                        );
                       }, 
                      ),
                    ),
                  ],
                ),
                const SizedBox(height: 20),
                Row(
                  mainAxisAlignment: MainAxisAlignment.center,
                  children: [
                    const Text('Aún no tienes cuenta? Regístrate '),
                    InkWell(
                      child: const Text(
                        'aquí',
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


  Future<void> _loginMethod() async{
    final loginProvider = Provider.of<LoginProvider>(context);

    SocketProvider socket = Provider.of<SocketProvider>(context, listen: false);
    
    bool loginResponse = await loginProvider.login(_userNameController.text, _passwordController.text);
      
    if(loginResponse){
      socket.connectStomp();
    
      /*Las modificaciones sobre la interfaz se deben realizar en el hilo principal
        no en llamadas asíncronas. Para ejecutar la navegación a la nueva página
        en el hilo principal usamos Future.microtask*/
      Future.microtask(() => Navigator.of(context).pushReplacementNamed('contacts'));
    }else{
      Future.delayed(Duration.zero, () {
        ScaffoldMessenger.of(context).showSnackBar(
          SnackBar(
            content: const Center(child: Text('Error al iniciar sesión')),
            behavior: SnackBarBehavior.floating,
            shape: RoundedRectangleBorder(
              borderRadius: BorderRadius.circular(10),
            ),
            margin: const EdgeInsets.all(16),
          ),
        );
      });
    }
  }
}