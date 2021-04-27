package SMS;

import java.net.InetSocketAddress;
import java.net.Socket;
import java.security.KeyPair;
import java.util.Optional;
import javafx.application.Application;
import javafx.scene.control.TextInputDialog;
import javafx.stage.Stage;
/**
 * 
 * @author Anna Turner
 *
 */
public class WhichClient {

    Stage stage;

    public WhichClient(Stage s, KeyPair kp) {
        stage = s;
        Socket connection; // declare a "general" socket

        try
        {
            // create a connection to the server
            connection = new Socket ();
            connection.connect(new InetSocketAddress(getIP(),getPort()),2000);
            System.out.println("Connection Established");
            new ChatClient(stage,connection,getName(),kp);

        }
        catch(Exception e)
        {
            System.out.println("Starting Server");
            new ChatServer(getName(), stage, kp);

        }
    }


    private String getIP(){
        // get address of host
        TextInputDialog textDialog1 = new TextInputDialog();
        textDialog1.setHeaderText("Enter remote host");
        textDialog1.setTitle("Chat Client");
        return(textDialog1.showAndWait().get());
    }

    private int getPort(){
        // get port number
        TextInputDialog textDialog2 = new TextInputDialog();
        textDialog2.setHeaderText("Enter port number");
        textDialog2.setTitle("Chat Client");
        return Integer.valueOf(textDialog2.showAndWait().get());
    }

    private String getName(){
        // get user name
        TextInputDialog textDialog3 = new TextInputDialog();
        textDialog3.setHeaderText("Enter user name");
        textDialog3.setTitle("Chat Client");
        return(textDialog3.showAndWait().get());
    }
}
