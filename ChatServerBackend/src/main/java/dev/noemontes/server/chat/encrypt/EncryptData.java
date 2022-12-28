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
	private static final String SECRET_KEY = "z/wqRxDgH8oed5j4YmKPfA==";
	private static final String SECRET_IV = "8CMRnCAfKIl7izIjtI8j9Q==";
	private static final String AES_ALGORITHM = "AES";
	
	
	public static String encryptData(String data) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException, InvalidAlgorithmParameterException{
		Cipher cipher = Cipher.getInstance(AES_ALGORITHM);
	    cipher.init(Cipher.ENCRYPT_MODE, getSecretKey(SECRET_KEY));
	    byte[] encryptedData = cipher.doFinal(data.getBytes());
	    return Base64.getEncoder().encodeToString(encryptedData);
	}
	
	public static String decryptData(String encryptData) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException, InvalidAlgorithmParameterException{
		Cipher cipher = Cipher.getInstance(AES_ALGORITHM);
	    cipher.init(Cipher.DECRYPT_MODE, getSecretKey(SECRET_KEY));
	    byte[] decryptedData = cipher.doFinal(Base64.getDecoder().decode(encryptData));
	    return new String(decryptedData);
	}
	
	private static SecretKey getSecretKey(String secretKey) {
		byte[] key = secretKey.getBytes();
		return new SecretKeySpec(key, AES_ALGORITHM);
	}
	
	
	private static IvParameterSpec generateIv() {
		byte[] iv = SECRET_IV.getBytes();
		
	    return new IvParameterSpec(iv);
	}
}
