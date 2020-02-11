import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import view.View;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        int size = 4;
        View.setInstance(primaryStage, size);
    }


    public static void main(String[] args) {
        launch(args);
    }
}
