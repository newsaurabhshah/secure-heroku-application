package com.demo.utilities;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * @author saurabhss
 * 
 */
public class CrpticUtility {
	static Log log = LogFactory.getLog(CrpticUtility.class);

	private static byte[] key = { 0x74, 0x68, 0x69, 0x73, 0x49, 0x73, 0x41,

			0x53, 0x65, 0x63, 0x72, 0x65, 0x74, 0x4b, 0x65, 0x79 };// "thisIsASecretKey";

	public static String decrypt(String strToDecrypt) {

		try {

			Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5PADDING");

			final SecretKeySpec secretKey = new SecretKeySpec(key, "AES");

			cipher.init(Cipher.DECRYPT_MODE, secretKey);

			final String decryptedString = new String(cipher.doFinal(Base64

					.decodeBase64(strToDecrypt)));

			return decryptedString;

		} catch (Exception e) {

			log.error("Error while decrypting", e);

		}

		return null;

	}

	public static String encrypt(String strToEncrypt) {

		try {

			Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");

			final SecretKeySpec secretKey = new SecretKeySpec(key, "AES");

			cipher.init(Cipher.ENCRYPT_MODE, secretKey);

			final String encryptedString = Base64.encodeBase64String(cipher

					.doFinal(strToEncrypt.getBytes()));

			return encryptedString;

		} catch (Exception e) {

			log.error("Error while encrypting", e);

		}

		return null;

	}

	public static void main(String args[]) {

		// Encrypt

		final String strToEncrypt = "saurabh.shah";// cmd.getOptionValue("encrypt");

		final String encryptedStr = CrpticUtility.encrypt(strToEncrypt.trim());

		System.out.println("String to Encrypt : " + strToEncrypt);

		System.out.println("Encrypted : " + encryptedStr);

		// decrypt

		final String strToDecrypt = encryptedStr;// cmd.getOptionValue("decrypt");

		final String decryptedStr = CrpticUtility.decrypt(strToDecrypt.trim());

		System.out.println("String To Decrypt : " + strToDecrypt);

		System.out.println("Decrypted : " + decryptedStr);

	}
}
