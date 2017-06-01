package com.g2ops.washington.utils;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.util.Arrays;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

public class PasscodeEncryptionService {

	public boolean authenticate(String attemptedPasscode, byte[] encryptedPasscode, byte[] salt)
			throws NoSuchAlgorithmException, InvalidKeySpecException {

		// Encrypt the clear-text passcode using the same salt that was used to encrypt the original passcode
		byte[] encryptedAttemptedPasscode = getEncryptedPasscode(attemptedPasscode, salt);

		// Authentication succeeds if encrypted passcode that the user entered is equal to the stored hash
		return Arrays.equals(encryptedPasscode, encryptedAttemptedPasscode);

	}

	public byte[] getEncryptedPasscode(String passcode, byte[] salt)
			throws NoSuchAlgorithmException, InvalidKeySpecException {

		// PBKDF2 with SHA-1 as the hashing algorithm. Note that the NIST specifically names SHA-1 as an acceptable hashing algorithm for PBKDF2
		String algorithm = "PBKDF2WithHmacSHA1";

		// SHA-1 generates 160 bit hashes, so that's what makes sense here
		int derivedKeyLength = 160;

		// Pick an iteration count that works for you. The NIST recommends at least 1,000 iterations:
		// http://csrc.nist.gov/publications/nistpubs/800-132/nist-sp800-132.pdf
		int iterations = 20000;

		KeySpec spec = new PBEKeySpec(passcode.toCharArray(), salt, iterations, derivedKeyLength);
		SecretKeyFactory f = SecretKeyFactory.getInstance(algorithm);

		return f.generateSecret(spec).getEncoded();

	}

	public byte[] generateSalt() throws NoSuchAlgorithmException {

		// VERY important to use SecureRandom instead of just Random
		SecureRandom random = SecureRandom.getInstance("SHA1PRNG");

		// Generate a 8 byte (64 bit) salt as recommended by RSA PKCS5
		byte[] salt = new byte[8];
		random.nextBytes(salt);

		return salt;
	}

}
