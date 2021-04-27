package SMS;

import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

/**
 * Crypto Class
 * @author Liam Walton
 * This class is responsible for encrypting data for the application.
 */
public class Crypto extends GenKeys {
	public static PublicKey getKeySpec() {
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
	}//End of getKeySpec
	
	public static byte[] encrypt(String data) throws BadPaddingException, IllegalBlockSizeException,
													InvalidKeyException, NoSuchPaddingException, 
													NoSuchAlgorithmException
	{
		//Create Cipher
		
		return 0;
	}
}
