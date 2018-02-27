package com.g2ops.impact.urm.utils;

import java.security.Key;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;

public class EncryptionDecryptionService {

	private static final String ALGORITHM = "AES";
	private static final byte[] keyValue = new byte[]{'S', 'e', 'c', 'r', 'e', 't', 'W', 'a', 's', 'h', 'i', 'n', 'g', 't', 'o', 'n'};

	/**
	* Encrypt a string with AES algorithm
	*
	* @param data is a string
	* @return the encrypted string
	*/
	public static String encrypt(String data) throws Exception {
		Key key = generateKey();
		Cipher c = Cipher.getInstance(ALGORITHM);
		c.init(Cipher.ENCRYPT_MODE, key);
		byte[] encryptedValue = c.doFinal(data.getBytes());
		return Base64.getEncoder().encodeToString(encryptedValue);
	}

	/**
	* Decrypt a string with AES algorithm
	*
	* @param encryptedData is a string
	* @return the decrypted string
	*/
	public static String decrypt(String encryptedData) throws Exception {
		Key key = generateKey();
		Cipher c = Cipher.getInstance(ALGORITHM);
		c.init(Cipher.DECRYPT_MODE, key);
		byte[] decodedValue = Base64.getDecoder().decode(encryptedData);
		byte[] decryptedValue = c.doFinal(decodedValue);
		return new String(decryptedValue);
	}

	/**
	* Generate a new encryption key.
	*/
	private static Key generateKey() throws Exception {
		return new SecretKeySpec(keyValue, ALGORITHM);
	}

}
