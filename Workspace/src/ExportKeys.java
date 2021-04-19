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
public class ExportKeys {
	
	public void exportKeys(KeyPair kpIn) {
		try(
				FileOutputStream keyFile = new FileOutputStream("Keys.dat");
				ObjectOutputStream keyStream = new ObjectOutputStream(keyFile)
			)//End of resources
		{
			keyStream.writeObject(kpIn);//Write out KeyPair Object
		}//End of try
		catch(IOException e) {
			System.out.println("An IO Exception occured, unable to write file.");
		}//End of catch
	}//End of exportKeys
	
	public KeyPair readKeys() {
		KeyPair kp = null;
		try
		(
			FileInputStream keyFile = new FileInputStream("Keys.dat");
			ObjectInputStream keyStream = new ObjectInputStream(keyFile);
		)//End of Resources
		{
			kp = (KeyPair) keyStream.readObject();//Read in KeyPair object to kp
		}//End of try
		catch(Exception e) {
			e.printStackTrace();
		}//End of catch
		return kp;
	}//End of readKeys
}//End of class
