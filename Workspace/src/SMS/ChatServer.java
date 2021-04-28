package SMS;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.security.*;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.X509EncodedKeySpec;
import javax.xml.bind.DatatypeConverter;


/**
 * 
 * @author Quentin Charatan & Aaron Khans (Java in Two Semesters 4th Ed., Liam Walton, Anna Turner
 *
 */
public class ChatServer {
    // declare and initialise the text display area

    private final int port = 8901;

    private Socket connection; // declare a "general" socket
    private ServerSocket listenSocket; // declare a server socket
    private OutputStream outStream; // for low level output
    private DataOutputStream outDataStream; // for high level output

    private TextArea textWindow = new TextArea();
    private Stage stage;
    private String name;
    private ServerListenerTask listener;



    public ChatServer(String n, Stage s, KeyPair kp) {
        name = n;
        stage = s;
        GUI();
        Crypto.setLocalKEys(kp);


		
		textWindow.appendText("Listening for connection" + "\n");
        try
        {
            // create a server socket
            listenSocket = new ServerSocket(port);

            // create a thread to listen for messages
            listener = new ServerListenerTask(textWindow, listenSocket, kp);
            Thread thread = new Thread(listener);
            thread.start(); // start the thread
        }
        catch (IOException e)
        {
            textWindow.setText("An error has occurred");
        }
    }

    public void GUI()
    {
        TextField inputWindow = new TextField();

        // configure the behaviour of the input window
        inputWindow.setOnKeyReleased(e ->
                {
                	if(e.getCode().getName().equals("Enter"))  // if the <Enter> key was pressed
                    {
                		if(listener.isConnected()) {
                    		connection = listener.getConnection();
                    		String text;
                    		text = "<" + name + "> " +  inputWindow.getText() + "\n";
                            textWindow.appendText(text); // echo the text
                            inputWindow.setText(""); // clear the input window

                            try
                            {
                            	outStream = connection.getOutputStream();
                                outDataStream = new DataOutputStream (outStream);
                                outDataStream.writeUTF(Crypto.encrypt(text)); // transmit the text
                            }
                            catch(Exception ie)
                            {
                            }  
                		}else{
                    		textWindow.appendText("Waiting for connection" + "\n");
                    	}
                    }
                });

        // configure the visual components
        textWindow.setEditable(false);
        textWindow.setWrapText(true);
        VBox root = new  VBox();
        root.setAlignment(Pos.CENTER);
        root.getChildren().addAll(textWindow, inputWindow);
        Scene scene = new Scene(root, 500, 300);
        stage.setScene(scene);
        stage.setTitle(name);
        stage.show();
    }
    
  
}
