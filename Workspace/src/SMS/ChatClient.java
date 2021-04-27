package SMS;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.nio.ByteBuffer;
import java.security.*;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.X509EncodedKeySpec;

import javax.xml.bind.DatatypeConverter;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ChatClient {
    private TextArea textWindow = new TextArea(); // declare and initialise the text display area
    private OutputStream outStream; // for low level output
    private DataOutputStream outDataStream; // for high level output
    private ListenerTask listener; // required for the server thread
    private Socket connection; // Active connection to server
    private String name; //User's name
    private Stage stage;
    private KeyPair keyPair;
    private PublicKey pubKey;

    public ChatClient(Stage s, Socket c, String n, KeyPair kp) {
        connection = c;
        name = n;
        stage = s;
        keyPair = kp;
        gui();
        try
        {
            // create output stream to the connection
            outStream = connection.getOutputStream();
            outDataStream = new DataOutputStream (outStream);

            // create a thread to listen for messages
            listener = new ListenerTask(textWindow, connection);
            Thread thread = new Thread(listener);
            thread.start(); // start the thread
        }
        catch(UnknownHostException e)
        {
            textWindow.setText("Unknown host");
        }
        catch (IOException e)
        {
            textWindow.setText("An error has occured");
        }
        exchangeKeys();
    }

    private void gui(){
        TextField inputWindow = new TextField();
        // configure the behaviour of the input window
        inputWindow.setOnKeyReleased(e ->
                {
                    String text;
                    if(e.getCode().getName().equals("Enter"))   // if the <Enter> key was pressed
                    {
                        text = "<" + name + "> " +  inputWindow.getText() + "\n";
                        textWindow.appendText(text); // echo the text
                        inputWindow.setText(""); // clear the input window

                        try
                        {
                            //Encrypt Here
                        	text = Crypto.encrypt(text);
                        	outDataStream.writeUTF(text); // transmit the text
                        }
                        catch(IOException ie)
                        {
                        } catch (Exception e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
                    }
                }
        );

        // configure the visual components
        textWindow.setWrapText(true);
        textWindow.setEditable(false);
        VBox root = new VBox();
        root.setAlignment(Pos.CENTER);
        root.getChildren().addAll(textWindow, inputWindow);
        Scene scene = new Scene(root, 500, 300);
        stage.setScene(scene);
        stage.setTitle(name);
        stage.show();
    }

    private void exchangeKeys(){

    	
        try {
            System.out.println(DatatypeConverter.printHexBinary(keyPair.getPublic().getEncoded()));
            outStream.write(keyPair.getPublic().getEncoded());
            outStream.flush();
        } catch (IOException e) {
            System.out.println("I/O Error");
            System.exit(0);
        }
        
        while(pubKey.equals(null))
        {
            try
            {
            	byte[] servPubKeyBytes = new byte[2048];
            	connection.getInputStream().read(servPubKeyBytes);
                System.out.println(DatatypeConverter.printHexBinary(servPubKeyBytes));
                
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
        }

    }
}

