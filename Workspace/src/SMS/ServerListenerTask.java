package SMS;
import java.io.*;
import java.net.Socket;
import javafx.concurrent.Task;
import javafx.scene.control.TextArea;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.X509EncodedKeySpec;

import javafx.concurrent.Task;
import javafx.scene.control.TextArea;

import javax.xml.bind.DatatypeConverter;

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
	private PublicKey pubKey;
	private KeyPair keyPair;
	private OutputStream outStream; // for low level output

    // constructor receives references to the text area and the connection
    public ServerListenerTask(TextArea windowIn, ServerSocket ls, KeyPair kp)
    {
        window = windowIn;
        listenSocket = ls;
        connection = new Socket();
		keyPair = kp;
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
            		connection = listenSocket.accept();
            		exchangeKeys();
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

	private void exchangeKeys(){
		try
		{
			System.out.println("1");
			byte[] servPubKeyBytes = new byte[588];
			System.out.println("2");
			connection.getInputStream().read(servPubKeyBytes);
			System.out.println("3");
			System.out.println(DatatypeConverter.printHexBinary(servPubKeyBytes));
			System.out.println("4");
			X509EncodedKeySpec ks = new X509EncodedKeySpec(servPubKeyBytes);
			KeyFactory kf = KeyFactory.getInstance("RSA");
			pubKey = kf.generatePublic(ks);
			System.out.println(DatatypeConverter.printHexBinary(pubKey.getEncoded()));
		} catch (IOException e) {
			System.out.println("Error obtaining server public key 1.");
			System.exit(0);
		} catch (NoSuchAlgorithmException e) {
			System.out.println("Error obtaining server public key 2.");
			System.exit(0);
		} catch (InvalidKeySpecException e) {
			System.out.println("Error obtaining server public key 3.");
			System.exit(0);
		}

		try {
			System.out.println(DatatypeConverter.printHexBinary(keyPair.getPublic().getEncoded()));
			outStream = connection.getOutputStream();
			outStream.write(keyPair.getPublic().getEncoded());
			outStream.flush();
		} catch (IOException e) {
			System.out.println("I/O Error");
			System.exit(0);
		}

	}
}
