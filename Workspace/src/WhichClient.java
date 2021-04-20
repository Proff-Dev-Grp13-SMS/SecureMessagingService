import java.net.Socket;
import java.util.Optional;
import javafx.application.Application;
import javafx.scene.control.TextInputDialog;
import javafx.stage.Stage;

public class WhichClient  extends Application{
	
	   @Override
	    public void start(Stage stage)   
	    {
		   	String remoteMachine; // to hold the name chosen by the user
		   	int port; // to hold the port number of the server
		   	String name;
		    Optional<String> response;
		     
		    // get address of host 
		    TextInputDialog textDialog1 = new TextInputDialog();
		    textDialog1.setHeaderText("Enter remote host");
		    textDialog1.setTitle("Chat Client");
		    response = textDialog1.showAndWait();
		    remoteMachine = response.get(); 
		                 
		    // get port number            
		    TextInputDialog textDialog2 = new TextInputDialog();
		    textDialog2.setHeaderText("Enter port number");
		    textDialog2.setTitle("Chat Client");
		    response = textDialog2.showAndWait(); 
		    port = Integer.valueOf(response.get());
		       
		    // get user name
		    TextInputDialog textDialog3 = new TextInputDialog();
		    textDialog3.setHeaderText("Enter user name");
		    textDialog3.setTitle("Chat Client");
		    response = textDialog3.showAndWait();
		    name =  response.get();
	       
		    
		    Socket connection; // declare a "general" socket
	       
	        
	        try
	        {
	            // create a connection to the server
	            connection = new Socket (remoteMachine, port);
	            System.out.println("Connection Established");
	            new ChatClient(connection,name);
	            
	        }  
	        catch(Exception e)
	        {	
	        	System.out.println("Starting Server");
	        	new ChatServer(name);
	        	
	        } 
	    }
	   
	   
	   
	   public static void main(String[] args)
	   {
	       launch(args);
	   }
	   
	   @Override
	   public void stop()
	   {
	       System.exit(0); // terminate application when the window is closed
	   }
}
