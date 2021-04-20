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


public class ChatServer extends Application
{ 
    // declare and initialise the text display area
    private TextArea textWindow = new TextArea();
    private OutputStream outStream; // for low level output
    private DataOutputStream outDataStream; // for high level output
    private ListenerTask listener; // required for the server thread
    private final int port = 8901;
    private String name;
    private Socket connection; // declare a "general" socket
    private ServerSocket listenSocket; // declare a server socket
    
    public ChatServer(String n) {
        name = n;
        System.out.println("Listening for connection");
        try  
        {
            // create a server socket
	        listenSocket = new ServerSocket(port);  	    
	        
            // listen for a connection from the client
            connection = listenSocket.accept();		       

	        // create an output stream to the connection   
	        outStream = connection.getOutputStream ();
	        outDataStream = new DataOutputStream(outStream );

	        // create a thread to listen for messages
           listener = new ListenerTask(textWindow, connection);

           Thread thread = new Thread(listener);
           thread.start(); // start the thread  
           
       }  
	    catch (IOException e)  
	    {		 
	        textWindow.setText("An error has occured");  	  
	    }      	
    }
    

    @Override    
    public void start(Stage stage)
    {  
        TextField inputWindow = new TextField();

        // configure the behaviour of the input window
        inputWindow.setOnKeyReleased(e -> 
                    {  
                        String text;

                        if(e.getCode().getName().equals("Enter"))  // if the <Enter> key was pressed
                        {
                            text = "<" + name + "> " +  inputWindow.getText() + "\n";
                            textWindow.appendText(text); // echo the text
                            inputWindow.setText(""); // clear the input window

                            try
                            {
                                outDataStream.writeUTF(text); // transmit the text  
                            }

                            catch(IOException ie)
                            {  
                            }
                        }
                    }
                                    );

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
   
   
   @Override   
   public void stop()
   {
       System.exit(0); // terminate application when the window is closed
   }
   
   public static void main(String[] args)
   {
       launch(args);
   }
}

