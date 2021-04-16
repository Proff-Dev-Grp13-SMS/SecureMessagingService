package something.witty.secure_messaging_service;

import java.security.*;
public class GenSig {
	private static Signature sigName;
	
	private Signature generateSignature() throws NoSuchAlgorithmException {
		sigName = Signature.getInstance("SHA256withRSA");
		return sigName;
	}//End of generateSignature
	
	public Signature initSig() throws GeneralSecurityException {
		generateSignature();
		sigName.initSign(GenKeys.getPrivKey());
		return sigName;
	}//End of initSig
}//End of GenSig
