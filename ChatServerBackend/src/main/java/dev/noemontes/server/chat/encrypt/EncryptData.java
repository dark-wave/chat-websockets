package dev.noemontes.server.chat.encrypt;

import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class EncryptData {
	private static final String SECRET_KEY_STRING = "z/wqRxDgH8oed5j4YmKPfA==";
	private static final byte[] SECRET_KEY = {-23, 112, -14, 116, 111, -14, 35, 96, -91, 55, 62, -77, -64, 85, 71, 117};
	private static final byte[] SECRET_IV = {-23, 112, -14, 116, 111, -14, 35, 96, -91, 55, 62, -77, -64, 85, 71, 117};
	private static final String AES_ALGORITHM = "AES/CBC/PKCS5Padding";
	
	
	public static String encryptData(String data) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException, InvalidAlgorithmParameterException{
		Cipher cipher = Cipher.getInstance(AES_ALGORITHM);
	    cipher.init(Cipher.ENCRYPT_MODE, getSecretKey(SECRET_KEY_STRING), generateIv());
	    byte[] encryptedData = cipher.doFinal(data.getBytes());
	    
	    return Base64.getEncoder().encodeToString(encryptedData);
	}
	
	public static String decryptData(String encryptData) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException, InvalidAlgorithmParameterException{
		Cipher cipher = Cipher.getInstance(AES_ALGORITHM);
	    cipher.init(Cipher.DECRYPT_MODE, getSecretKey(SECRET_KEY_STRING), generateIv());
	    byte[] decryptedData = cipher.doFinal(Base64.getDecoder().decode(encryptData));
	    return new String(decryptedData);
	}
	
	private static SecretKey getSecretKey(String secretKey) {
		byte[] key = SECRET_KEY;
		return new SecretKeySpec(key, "AES");
	}
	
	
	private static IvParameterSpec generateIv() {
		byte[] iv = SECRET_IV;
		
	    return new IvParameterSpec(iv);
	}
}
