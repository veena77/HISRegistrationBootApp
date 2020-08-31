package com.ashokit.util;

import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.springframework.stereotype.Component;

@Component
public class PwdUtil {

	private static final String INIT_VECTOR="EncryptionIntVec";
	private static final String SECRET_KEY="EncryptionSecKey";

	public static String generateTempPassword(int n) 
	{ 

		// chose a Character random from this String 
		String AlphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
									+ "0123456789"
									; 

		// create StringBuffer size of AlphaNumericString 
		StringBuilder sb = new StringBuilder(n); 

		for (int i = 0; i < n; i++) { 

			// generate a random number between 
			// 0 to AlphaNumericString variable length 
			int index 
				= (int)(AlphaNumericString.length() 
						* Math.random()); 

			// add Character one by one in end of sb 
			sb.append(AlphaNumericString 
						.charAt(index)); 
		} 

		return sb.toString(); 
	}
	public static String encryptPwd(String pwd) {
		
		try {
			IvParameterSpec ivParamSpec=new IvParameterSpec(INIT_VECTOR.getBytes());
			SecretKeySpec keySpec=new SecretKeySpec(SECRET_KEY.getBytes("UTF-8"), "AES");
			Cipher cipher=Cipher.getInstance("AES/CBC/PKCS5PADDING");
			cipher.init(Cipher.ENCRYPT_MODE,keySpec, ivParamSpec);
			
			byte[] encrypted=cipher.doFinal(pwd.getBytes());
			byte[] encoded=Base64.getEncoder().encode(encrypted);
			return new String(encoded);
		}catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	}

