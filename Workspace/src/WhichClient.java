import java.io.DataOutputStream;
import java.io.OutputStream;
import java.net.Socket;

import javafx.scene.control.TextArea;

public class WhichClient {


	private boolean detectVersion(String ip, int port) {
		Socket connection;  // declare a "general" socket
		try
        {
            // create a connection to the server
            connection = new Socket (ip, port); 
            connection.close();
            return true;
        }
		catch(Exception e) {
			return false;
		}
	}
	

}
