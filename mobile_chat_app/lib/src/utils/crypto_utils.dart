import 'package:encrypt/encrypt.dart';

class CrytoUtils{
  static const String SECRET_KEY = "6XDydG/yI2ClNz6zwFVHdQ==";
  static const String SECRET_IV = "6XDydG/yI2ClNz6zwFVHdQ==";
  static const IV_ARRAY = [-23, 112, -14, 116, 111, -14, 35, 96, -91, 55, 62, -77, -64, 85, 71, 117];

  static String encryptString(String data){
    Encrypted encrypted = encryptWithAES(SECRET_KEY, data);
    String encryptedBase64 = encrypted.base64;

    return encryptedBase64;
  }

  static Encrypted encryptWithAES(String key, String plainText) {
    final cipherKey = Key.fromBase64(key);
    //final encryptService = Encrypter(AES(cipherKey, mode: AESMode.cbc));
    final encryptService = Encrypter(AES(cipherKey, mode: AESMode.cbc));
    final initVector = IV.fromBase64(SECRET_KEY);
 
    Encrypted encryptedData = encryptService.encrypt(plainText, iv: initVector);
    return encryptedData;
  }
}
