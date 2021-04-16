/**
 * @author liam
 * This is the class responsible for generating the keys required for the user.
 */
import java.security.*;
public class GenKeys {
	//Private Variables
	private KeyPairGenerator keyGen;
	private static KeyPair kp;
	private static SecureRandom sr;
	
	/**
	 * This function is designed to return a secure, usable random number 
	 * @return SecureRandom sr: Returns an initialised SecureRandom for use in generateKeys()
	 */
	private SecureRandom generateSecureRandom() {
		try {
			sr = SecureRandom.getInstance("SHA1PRNG", "SUN");//Generation of SecureRandom
		} catch (NoSuchAlgorithmException | NoSuchProviderException e) {
			e.printStackTrace();
		}//End of try/catch
		return sr;
	}//End of generateSecureRandom
	
	/**
	 * This function is responsible for generating the key pair for crypto 
	 */
	public void generateKeys() {
		try {
			generateSecureRandom();//Create the sr
			keyGen = KeyPairGenerator.getInstance("AES");
			keyGen.initialize(256, sr);
			kp = keyGen.generateKeyPair();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}//End of try/catch
	}//End of generateKeys
	
	/**
	 *  This function returns the private key from the key pair
	 * @return PrivateKey: The private key of the key pair
	 */
	public static PrivateKey getPrivKey() {
		return kp.getPrivate();
	}//End of getPrivKey
	
	/**
	 *  This function returns the public key from the key pair
	 * @return PublicKey: The public key of the key pair
	 */
	public static PublicKey getPubKey() {
		return kp.getPublic();
	}
}//End of GenKeys
