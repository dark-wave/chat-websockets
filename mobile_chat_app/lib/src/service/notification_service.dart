import 'package:flutter/material.dart';
import 'package:flutter_local_notifications/flutter_local_notifications.dart';

//Clase que contiene la lógica de negocio de las notificaciones
class NotificationService{

  //Constructor público
  NotificationService();

  final FlutterLocalNotificationsPlugin _flutterLocalNotificationsPlugin = FlutterLocalNotificationsPlugin();

  //Método que configura las notificaciones en ios y android
  Future<void> setupLocalNotifications() async{
    const androidInitializationSetting = AndroidInitializationSettings('@mipmap/ic_launcher');
    const iosInitializationSetting = DarwinInitializationSettings();
    
    const initializationSettings = InitializationSettings(android: androidInitializationSetting, iOS: iosInitializationSetting);

    await _flutterLocalNotificationsPlugin.initialize(initializationSettings);
  }

  // Método que muestra una notificación local
  void showLocalNotification(String title, String body){
    const AndroidNotificationDetails androidNotificationDetail = AndroidNotificationDetails(
      '000', //Channel id
      'chat_app_channel', //Channel name  
      channelDescription: 'Canal de comunicacion de notificaciones de flutter chat', //Channel description
      importance: Importance.max,
      priority: Priority.high,
      showWhen: true, //Mostramos la hora
      autoCancel: false,// No se cierra automáticamente
      actions: <AndroidNotificationAction>[
        AndroidNotificationAction(
          'action_accept',
          'Aceptar'
        ),
        AndroidNotificationAction(
          'action_cancel',
          'Rechazar',
          titleColor: Color.fromARGB(255, 255, 0, 0),
        ),
      ]
    );

    const iosNotificatonDetail = DarwinNotificationDetails(
      presentAlert: true,
      presentBadge: true,
      presentSound: true,
      subtitle: 'Notificación de chat',
      threadIdentifier: 'chat_app_notification'
    );

    const notificationDetails = NotificationDetails(
      iOS: iosNotificatonDetail,
      android: androidNotificationDetail,
    );

    _flutterLocalNotificationsPlugin.show(0, title, body, notificationDetails);
  }
  
  //Metodo lanzado al pulsar sobre los botones de la notificación
  Future<void> onNotificationAction(String? action) async {
    if (action == 'action_accept') {
      print('Hola has pulsado aceptar');
    } else if (action == 'action_cancel') {
      print('Has pulsado cancelar');
    }
  }
}