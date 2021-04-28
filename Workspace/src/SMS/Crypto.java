package SMS;

import java.security.*;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

/**
 * Crypto Class
 * @author Liam Walton
 * This class is responsible for encrypting data for the application.
 * Code snippets used/adapted from: 
 * https://www.devglan.com/java8/rsa-encryption-decryption-java#/google_vignette
 * https://examples.javacodegeeks.com/core-java/security/get-bytes-of-a-key-pair-example/
 * https://www.quickprogrammingtips.com/java/java-asymmetric-encryption-decryption-example-with-rsa.html
 */
public class Crypto {
	//MUH KEYS
	private static KeyPair keyPair;
	private static PublicKey foreignKey;
	
	/**
	 * Function is responsible for making a usable PublicKey for the user
	 * @return PublicKey returnMe: A usable public key for encryption
	 */
	private static PublicKey getKeySpecPK() {
		PublicKey myKey = keyPair.getPublic();//Key to get bytes from
		PublicKey returnMe = null;//New Key to return
		
		try {
			//Create KeySpec for Cipher
			X509EncodedKeySpec keySpec = new X509EncodedKeySpec(Base64.getDecoder().decode(myKey.getEncoded()));
			KeyFactory keyFactory = KeyFactory.getInstance("RSA");
			returnMe = keyFactory.generatePublic(keySpec);
			
			return returnMe;
			
		} catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (InvalidKeySpecException e) {
            e.printStackTrace();
        }
		
		return returnMe;
	}//End of getKeySpecPK
	
	/**
	 * Function is responsible for making a usable PrivateKey
	 * @return PrivateKey returnMe: A usable private key for decryption
	 */
	private static PrivateKey getKeySpecPRK() {
		PrivateKey myKey = keyPair.getPrivate();//Key to convert
		PrivateKey returnMe = null;//Key to return
		
		try {
			//Create KeySpec object
			PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(Base64.getDecoder().decode(myKey.getEncoded()));
			KeyFactory keyFactory = KeyFactory.getInstance("RSA");
			returnMe = keyFactory.generatePrivate(keySpec);
		} catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (InvalidKeySpecException e) {
            e.printStackTrace();
        }
		
		return returnMe;
	}//End of getKeySpecPRK
	
	
	/**
	 * This function takes in a string of data and encrypts it
	 * @param data: The outgoing message to be encrypted
	 * @return Cipher cipher: The encrypted outgoing data
	 * @throws BadPaddingException
	 * @throws IllegalBlockSizeException
	 * @throws InvalidKeyException
	 * @throws NoSuchPaddingException
	 * @throws NoSuchAlgorithmException
	 */
	
	public static byte[] oldEncrypt(String data) throws BadPaddingException, IllegalBlockSizeException, 
	InvalidKeyException, NoSuchPaddingException, NoSuchAlgorithmException
	{
		//Create Cipher
		Cipher cipher = Cipher.getInstance("SA/None/OAEPWithSHA1AndMGF1Padding");
		cipher.init(Cipher.ENCRYPT_MODE, getKeySpecPK());
		return cipher.doFinal(data.getBytes());
	}//End of encrypt
	
	public static String oldDecrypt(String data) throws IllegalBlockSizeException, InvalidKeyException, BadPaddingException, 
	NoSuchAlgorithmException, NoSuchPaddingException
	{
		/*
		 * Attempted Workaround
		//Get Bytes of inc. message
		byte[] incoming = data.getBytes();
		//Decode/Decrypt
		incoming = Base64.getDecoder().decode(data.getBytes()), getKeySpecPRK();
		*/
		
		/*
		 * Below code is from source 1, I cannot get it to work.
		 * Above code is my attempt to make a workaround but doesn't work as I need something with name(Decode, key)
		 */
		//return new decrypt(Base64.getDecoder().decode(data.getBytes()), getKeySpecPRK());
		return "I'm a function that doesn't yet work! See comments!";
	}
	
	
	//New Classes, uses a much simpler, more applicable method for now. Above is probably much more secure
	/**
	 * PrivateKey Encryption of PlainText String
	 * @param plainText: The PlainText to be encrypted
	 * @return String: Encrypted String
	 * @throws Exception
	 */
	public static String encrypt(String plainText) throws Exception
	{
		//Cipher cipher = Cipher.getInstance("SA/None/OAEPWithSHA1AndMGF1Padding");
		Cipher cipher = Cipher.getInstance("RSA");
		cipher.init(Cipher.ENCRYPT_MODE, foreignKey);
		return Base64.getEncoder().encodeToString(cipher.doFinal(plainText.getBytes()));
	}//End of encrypt
	/**
	 * PublicKey Decryption of Encrypted PlainText String
	 * @param encryptedText
	 * @return String: The decrypted text
	 * @throws Exception
	 */
	public static String decrypt(String encryptedText) throws Exception
	{
		//Cipher cipher = Cipher.getInstance("SA/None/OAEPWithSHA1AndMGF1Padding");
		Cipher cipher = Cipher.getInstance("RSA");
		cipher.init(Cipher.DECRYPT_MODE, keyPair.getPrivate());
		return new String(cipher.doFinal(Base64.getDecoder().decode(encryptedText)));
	}

	public static void setForeignKey(PublicKey fk){
		foreignKey = fk;
	}

	public static void setLocalKEys(KeyPair kp){
		keyPair = kp;
	}
}//End of Class
