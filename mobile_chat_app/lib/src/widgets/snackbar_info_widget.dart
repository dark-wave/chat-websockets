import 'package:flutter/material.dart';
import 'package:mobile_chat_app/src/enum/snackbar_type.dart';


class SnackbarInfoWidget {
  static void showSnackbar(BuildContext context, SnackbarType type, String message) {
    Color backgroundColor;
    IconData iconData;

    switch (type) {
      case SnackbarType.info:
        backgroundColor = Colors.blue;
        iconData = Icons.info_outline;
        break;
      case SnackbarType.warning:
        backgroundColor = Colors.yellow;
        iconData = Icons.warning_outlined;
        break;
      case SnackbarType.error:
        backgroundColor = Colors.red;
        iconData = Icons.error_outline;
        break;
    }

    ScaffoldMessenger.of(context).showSnackBar(
      SnackBar(
        content: Row(
          children: [
            Icon(iconData, color: Colors.white),
            const SizedBox(width: 10),
            Text(message, style: const TextStyle(color: Colors.white))
          ],
        ),
        backgroundColor: backgroundColor,
        behavior: SnackBarBehavior.floating,
        shape: RoundedRectangleBorder(
          borderRadius: BorderRadius.circular(10),
        ),
        margin: const EdgeInsets.all(16)
      ),
    );
  }
}
