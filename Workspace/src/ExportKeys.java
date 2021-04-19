import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.security.KeyPair;

/**
 * @author Liam Walton
 * This class is responsible for exporting the KeyPair to a file for offline storage.
 */
public class ExportKeys extends GenKeys {
	
	/**
	 * Responsible for exporting the keys to offline storage. Uses Object Serialization. 
	 * @param KeyPair kpIn: The KeyPair to be exported.
	 */
	public void exportKeys() {
		try(
				FileOutputStream keyFile = new FileOutputStream("Keys.dat");
				ObjectOutputStream keyStream = new ObjectOutputStream(keyFile)
			)//End of resources
		{
			keyStream.writeObject(GenKeys.getKP());//Write out KeyPair Object
		}//End of try
		catch(IOException e) {
			System.out.println("An IO Exception occured, unable to write file.");
		}//End of catch
	}//End of exportKeys
	
	/**
	 * Imports previously exported KeyPair
	 * @return KeyPair keysIn: The KeyPair being read in from file
	 */
	public KeyPair readKeys() {
		KeyPair keysIn = null;
		try
		(
			FileInputStream keyFile = new FileInputStream("Keys.dat");
			ObjectInputStream keyStream = new ObjectInputStream(keyFile);
		)//End of Resources
		{
			keysIn = (KeyPair) keyStream.readObject();//Read in KeyPair object to kp
		}//End of try
		catch(Exception e) {
			e.printStackTrace();
		}//End of catch
		return keysIn;
	}//End of readKeys
}//End of class
