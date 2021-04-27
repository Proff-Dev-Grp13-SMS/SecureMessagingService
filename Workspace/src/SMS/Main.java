package SMS;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        new WhichClient(primaryStage, new GenKeys().getKP());
    }


    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void stop()
    {
        System.exit(0); // terminate application when the window is closed
    }
}
