package SMS;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import javafx.concurrent.Task;
import javafx.scene.control.TextArea;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import javafx.concurrent.Task;
import javafx.scene.control.TextArea;
/**
 * 
 * 
 * @author Quentin Charatan & Aaron Khans (Java in Two Semesters 4th Ed., Liam Walton, Anna Turner
 *
 */
public class ServerListenerTask extends Task{
    private InputStream inputStream; // for low level input
    private DataInputStream dataInputStream; // for high level input
    private TextArea window; // a reference to the text area where the message will be displayed
    private Socket connection; // a reference to the connection
    private ServerSocket listenSocket; // declare a server socket

    // constructor receives references to the text area and the connection
    public ServerListenerTask(TextArea windowIn, ServerSocket ls)
    {
        window = windowIn;
        listenSocket = ls;
        connection = new Socket();

    }

    @Override
    protected Void call()
    {
        String msg;
        while(true)
        {
        	while(connection.isConnected()==false) {
        		try
            		{
        			System.out.print(connection.isConnected()==false);
            		connection = listenSocket.accept();
            		// create an input stream from the remote machine
            		inputStream = connection.getInputStream();
            		dataInputStream = new DataInputStream(inputStream);
               		}
                	catch(Exception e)
                	{
                	}
        	}
		try
            	{
                msg = dataInputStream.readUTF(); // read the incoming message
                window.appendText(msg); // display the message
            	}
            	catch(IOException e)
            	{
            	}
	}
    }

    
    public Socket getConnection() {
    	return connection;
    }
    
    public boolean isConnected() {
    	if(connection!=null) {
    		return connection.isConnected();
    	}
    	else {
    		return false;
    	}
    	
    }
}
