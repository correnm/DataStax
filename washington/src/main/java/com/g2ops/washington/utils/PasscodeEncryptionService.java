package com.g2ops.washington.utils;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.util.Arrays;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

public class PasscodeEncryptionService {

	public static boolean authenticate(String attemptedPasscode, byte[] encryptedPasscode, byte[] salt, int iterations)
			throws NoSuchAlgorithmException, InvalidKeySpecException, UnsupportedEncodingException {

		// Encrypt the clear-text passcode using the same salt that was used to encrypt the original passcode
		byte[] encryptedAttemptedPasscode = getEncryptedPasscode(attemptedPasscode, salt, iterations);

		/*
		String saltString = new String(salt);
		String encryptedPasscodeString = new String(encryptedPasscode);
		String encryptedAttemptedPasscodeString = new String(encryptedAttemptedPasscode);
		System.out.println("*** PasscodeEncryptionService ***");
		System.out.println("attemptedPasscode: " + attemptedPasscode);
		System.out.println("iterations: " + iterations);
		System.out.println("saltString: " + saltString);
		System.out.println("encryptedPasscodeString: ***" + encryptedPasscodeString + "***");
		System.out.println("encryptedAttemptedPasscodeString: ***" + encryptedAttemptedPasscodeString + "***");
		System.out.println("*** PasscodeEncryptionService ***");
		*/
		
		// Authentication succeeds if encrypted passcode that the user entered is equal to the encrypted passcode from the DB
		return Arrays.equals(encryptedPasscode, encryptedAttemptedPasscode);

	}

	public static byte[] getEncryptedPasscode(String passcode, byte[] salt, int iterations)
			throws NoSuchAlgorithmException, InvalidKeySpecException, UnsupportedEncodingException {

		// PBKDF2 with SHA-1 as the hashing algorithm. Note that the NIST specifically names SHA-1 as an acceptable hashing algorithm for PBKDF2
		String algorithm = "PBKDF2WithHmacSHA1";

		// SHA-1 generates 160 bit hashes, so that's what makes sense here
		int derivedKeyLength = 160;

		// Pick an iteration count that works for you. The NIST recommends at least 1,000 iterations:
		// http://csrc.nist.gov/publications/nistpubs/800-132/nist-sp800-132.pdf

		KeySpec spec = new PBEKeySpec(passcode.toCharArray(), salt, iterations, derivedKeyLength);
		SecretKeyFactory f = SecretKeyFactory.getInstance(algorithm);

		// get byte array in UTF-16 (Java default encoding)
		byte[] tempBytes = f.generateSecret(spec).getEncoded();
		
		// convert byte array to a string
		String tempSecret = new String(tempBytes, "UTF-16");
		
		// convert string to a byte array in UTF-8 (Cassandra encoding)
		byte[] finalBytes = tempSecret.getBytes("UTF-8");
		
		/* return f.generateSecret(spec).getEncoded(); */
		// return a UTF-8 encoded byte array
		return finalBytes;

	}

	public static byte[] generateSalt() throws NoSuchAlgorithmException, UnsupportedEncodingException {

		// VERY important to use SecureRandom instead of just Random
		SecureRandom random = SecureRandom.getInstance("SHA1PRNG");

		// Generate a 8 byte (64 bit) salt as recommended by RSA PKCS5
		byte[] salt = new byte[8];
		random.nextBytes(salt);

		// convert byte array to a string
		String tempSalt = new String(salt, "UTF-16");
		
		// convert string to a byte array in UTF-8 (Cassandra encoding)
		salt = tempSalt.getBytes("UTF-8");
		
		// return a UTF-8 encoded byte array
		return salt;

	}

}
