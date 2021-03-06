package SMS;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import javafx.concurrent.Task;
import javafx.scene.control.TextArea;
/**
 * 
 * @author Quentin Charatan & Aaron Khans (Java in Two Semesters 4th Ed., Liam Walton, Anna Turner
 *
 */
public class ClientListenerTask extends Task{
    private InputStream inputStream; // for low level input
    private DataInputStream dataInputStream; // for high level input
    private TextArea window; // a reference to the text area where the message will be displayed
    private Socket connection; // a reference to the connection

    // constructor receives references to the text area and the connection
    public ClientListenerTask(TextArea windowIn, Socket connectionIn)
    {
        window = windowIn;
        connection = connectionIn;

        try
        {
            // create an input stream from the remote machine
            inputStream = connection.getInputStream();
            dataInputStream = new DataInputStream(inputStream);
        }

        catch(IOException e)
        {
        }
    }

    @Override
    protected Void call()
    {
        String msg;
        while(true)
        {
            try
            {
                msg = dataInputStream.readUTF(); // read the incoming message
                window.appendText(Crypto.decrypt(msg)); // display the message
            }
            catch(Exception e)
            {
            }
        }
    }
}