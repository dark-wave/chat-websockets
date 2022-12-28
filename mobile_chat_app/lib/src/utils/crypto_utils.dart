import 'package:encrypt/encrypt.dart';

class CrytoUtils{
  static const String SECRET_KEY = "z/wqRxDgH8oed5j4YmKPfA==";
  static const String SECRET_IV = "dZBObRkj7brsImgq";

  static String encryptString(String data){
    Encrypted encrypted = encryptWithAES(SECRET_KEY, data);
    String encryptedBase64 = encrypted.base64;

    return encryptedBase64;
  }

  static Encrypted encryptWithAES(String key, String plainText) {
    final cipherKey = Key.fromUtf8(key);
    final encryptService = Encrypter(AES(cipherKey, mode: AESMode.cbc));
    final initVector = IV.fromUtf8(SECRET_IV);
 
    Encrypted encryptedData = encryptService.encrypt(plainText, iv: initVector);
    return encryptedData;
  }
}
