package SMS;

import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;
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
 * Code snippets used/adapted from: https://www.devglan.com/java8/rsa-encryption-decryption-java#/google_vignette
 * 									https://examples.javacodegeeks.com/core-java/security/get-bytes-of-a-key-pair-example/
 */
public class Crypto extends GenKeys {
	/**
	 * Function is responsible for getting the key spec of the Public Key Generated for the user
	 * @return PublicKey returnMe: A usable public key for encryption
	 */
	private static PublicKey getKeySpecPK() {
		PublicKey myKey = kp.getPublic();//Key to get bytes from
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
	 * This function takes in a string of data and encrypts it
	 * @param String data: The outgoing message to be encrypted
	 * @return Cipher cipher: The encrypted outgoing data
	 * @throws BadPaddingException
	 * @throws IllegalBlockSizeException
	 * @throws InvalidKeyException
	 * @throws NoSuchPaddingException
	 * @throws NoSuchAlgorithmException
	 */
	public static byte[] encrypt(String data) throws BadPaddingException, IllegalBlockSizeException,
													InvalidKeyException, NoSuchPaddingException, 
													NoSuchAlgorithmException
	{
		//Create Cipher
		Cipher cipher = Cipher.getInstance("SA/None/OAEPWithSHA1AndMGF1Padding");
		cipher.init(Cipher.ENCRYPT_MODE, getKeySpecPK());
		return cipher.doFinal(data.getBytes());
	}//End of encrypt
}//End of Class
