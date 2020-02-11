package view;

import environment.Sudoku;
import environment.SudokuFactory;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class View {

    private static View view;

    private BorderPane root;
    private Sudoku sudoku;

    public View(Stage primaryStage) {
        try {
            root = new BorderPane();
            Button importButton = new Button("Import Sudoku");
            importButton.setOnAction(event -> {
                sudoku = SudokuFactory.getInstance().createSudoku(new FileChooser().showOpenDialog(primaryStage));
                update();
            });
            root.setTop(importButton);
            primaryStage.setTitle("Smart Agent");
            Scene scene = new Scene(root, 750, 805);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Error while trying to load the scene");
            System.exit(1);
        }
    }

    public void update() {
        if(sudoku != null) {
            GridPane gridPane = new GridPane();
            for (int i = 0; i < sudoku.getSize() ; i++) {
                ColumnConstraints column = new ColumnConstraints(750/sudoku.getSize());
                RowConstraints row = new RowConstraints(750/sudoku.getSize());
                gridPane.getColumnConstraints().add(column);
                gridPane.getRowConstraints().add(row);
            }
            gridPane.setGridLinesVisible(true);
            root.setCenter(gridPane);
            for(int i = 0 ; i < sudoku.getSize() ; i++) {
                for(int k = 0 ; k < sudoku.getSize() ; k++) {
                    if(sudoku.getData()[i][k] != 0) {
                        Text text = new Text(Integer.toString(sudoku.getData()[i][k]));
                        text.setFont(new Font(30));
                        gridPane.getChildren().add(text);
                        GridPane.setMargin(text, new Insets(0,0,0,30));
                        GridPane.setConstraints(text, i, k);
                    }
                }
            }
        }
    }

    public static void setInstance(Stage primaryStage, int size) {
        view = new View(primaryStage);
    }

    public static View getInstance() {
        return view;
    }

}
