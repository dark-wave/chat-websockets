import 'package:connectivity_plus/connectivity_plus.dart';
import 'package:flutter/material.dart';

class NetworkStatusWidget extends StatelessWidget {
  const NetworkStatusWidget({super.key});

  @override
  Widget build(BuildContext context) {
    Size size = MediaQuery.of(context).size;

    return StreamBuilder<ConnectivityResult>(
      stream: Connectivity().onConnectivityChanged,
      builder: (BuildContext context, AsyncSnapshot<ConnectivityResult> snapshot){
        if(snapshot.hasData){
          if(snapshot.data == ConnectivityResult.none){
            return Container(
              width: size.width,
              height: 30.0,
              color: Colors.red,
              child: const Text('No hay conexion a internet'),
            );
          }
        }
        return Container();
      },
    );
  }
}