import java.io.IOException;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class ImagesManager extends Application {
    
    @Override
    public void start(Stage primaryStage) throws IOException {
        
        Parent root = FXMLLoader.load(getClass().getResource("Main.fxml"));
        Scene scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
        primaryStage.setTitle("Images Manager");
        primaryStage.setScene(scene);
        primaryStage.show();
        primaryStage.setResizable(false);
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
