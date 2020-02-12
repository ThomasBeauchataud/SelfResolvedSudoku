import javafx.application.Application;
import javafx.stage.Stage;
import logic.SudokuResolver;
import view.View;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) {
        View.setInstance(primaryStage, new SudokuResolver());
    }


    public static void main(String[] args) {
        launch(args);
    }
}
