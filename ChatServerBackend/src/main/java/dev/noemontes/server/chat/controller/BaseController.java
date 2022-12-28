package dev.noemontes.server.chat.controller;

import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Base64;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.noemontes.server.chat.encrypt.EncryptData;

@RestController
@RequestMapping("/")
public class BaseController {

	@GetMapping("/test")
	public String test() {
		return "Rest controller is running";
	}
	
	@GetMapping("/getKey")
	public String getKey() throws NoSuchAlgorithmException {
		KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
		keyGenerator.init(128);
		
		return Base64.getEncoder().encodeToString(keyGenerator.generateKey().getEncoded());
	}
	
	@GetMapping("/getIv")
	public String getIv() {
		Random random = ThreadLocalRandom.current();
		byte[] r = new byte[16]; //Means 2048 bit
		random.nextBytes(r);
		String s = Base64.getEncoder().encodeToString(r);
		
		return s;
	}
	
	@GetMapping("/test/encrypt")
	public String testEncrypt(@RequestBody String data) throws InvalidKeyException, InvalidAlgorithmParameterException, NoSuchAlgorithmException, 
													NoSuchPaddingException, InvalidKeySpecException, IllegalBlockSizeException, BadPaddingException{
		return EncryptData.encryptData(data);
	}
	
	@GetMapping("/test/decrypt")
	public String testDecrypt(@RequestBody String encryptString) throws InvalidKeyException, NoSuchAlgorithmException, InvalidKeySpecException, 
													NoSuchPaddingException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException{
		return EncryptData.decryptData(encryptString);
	}
}
