/**
 * @author liam
 * This is the class responsible for generating the keys required for the user.
 */
import java.security.*;
public class GenKeys {
	//Private Variables
	private KeyPairGenerator keyGen;
	private KeyPair kp;
	private SecureRandom sr;
	
	/**
	 * This function is designed to return a secure, usable random number 
	 * @return SecureRandom sr: Returns an initialised SecureRandom for use in generateKeys()
	 */
	private SecureRandom generateSecureRandom() {
		try {
			sr.getInstance("SHA1PRNG", "SUN");
		} catch (NoSuchAlgorithmException | NoSuchProviderException e) {
			e.printStackTrace();
		}//End of try/catch
		return sr;
	}//End of generateSecureRandom
	
	/**
	 * 
	 */
	public void generateKeys() {
		try {
			keyGen = KeyPairGenerator.getInstance("AES");
		} catch (NoSuchAlgorithmException e) {
			
			e.printStackTrace();
		}//End of try/catch
	}
}
