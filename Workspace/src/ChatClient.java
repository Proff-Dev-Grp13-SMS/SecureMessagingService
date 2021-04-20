import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ChatClient extends Application
{
    private TextArea textWindow = new TextArea(); // declare and initialise the text display area
    private OutputStream outStream; // for low level output
    private DataOutputStream outDataStream; // for high level output
    private ListenerTask listener; // required for the server thread
    private Socket connection; // Active connection to server
    private String name; //User's name
    
  
    public ChatClient(Socket c, String n) {
    	connection = c;	
        name = n;
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
    }
    
    @Override
    public void start(Stage stage)   
    { 
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
                                outDataStream.writeUTF(text); // transmit the text  
                            }
                            catch(IOException ie)
                            {  
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

