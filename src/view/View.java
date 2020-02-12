package view;

import environment.Sudoku;
import environment.SudokuFactory;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import logic.SudokuResolver;

public class View {

    private static View instance;

    private SudokuResolver sudokuResolver;
    private Sudoku sudoku;
    private BorderPane root;
    private GridPane grid;

    public View(Stage primaryStage, SudokuResolver sudokuResolver) {
        this.sudokuResolver = sudokuResolver;
        loadScene(primaryStage);
        loadButtons(primaryStage);
    }

    public void update() {
        if(sudoku != null) {
            for(int i = 0 ; i < sudoku.getSize() ; i++) {
                for(int k = 0 ; k < sudoku.getSize() ; k++) {
                    Text text = ((Text)grid.getChildren().get(i*9 + k + 1));
                    if(sudoku.getData()[i][k] != 0  && text.getText().equals("")) {
                        ((Text)grid.getChildren().get(i*9 + k + 1)).setText(Integer.toString(sudoku.getData()[i][k]));
                    }
                }
            }
        }
    }

    private void initializeSudokuView() {
        grid = new GridPane();
        for (int i = 0; i < this.sudoku.getSize() ; i++) {
            ColumnConstraints column = new ColumnConstraints(750.0/this.sudoku.getSize());
            RowConstraints row = new RowConstraints(750.0/this.sudoku.getSize());
            grid.getColumnConstraints().add(column);
            grid.getRowConstraints().add(row);
        }
        grid.setGridLinesVisible(true);
        for(int i = 0 ; i < sudoku.getSize() ; i++) {
            for(int k = 0 ; k < sudoku.getSize() ; k++) {
                Text text = new Text();
                if(sudoku.getData()[i][k] != 0) {
                    text.setText(Integer.toString(sudoku.getData()[i][k]));
                }
                text.setFont(new Font(30));
                text.setFill(PaintProvider.getPaint((i/3)*3+(k/3)));
                grid.getChildren().add(text);
                GridPane.setMargin(text, new Insets(0,0,0,30));
                GridPane.setConstraints(text, k, i);
            }
        }
        root.setCenter(grid);
    }

    private void loadSudoku(Sudoku sudoku) {
        this.sudoku = sudoku;
        initializeSudokuView();
    }

    private void loadButtons(Stage primaryStage) {
        Pane topPane = new Pane();
        Button importButton = new Button("Import Sudoku");
        Button resolveButton = new Button("Resolve");
        importButton.setOnAction(event -> loadSudoku(SudokuFactory.getInstance().createSudoku(new FileChooser().showOpenDialog(primaryStage))));
        resolveButton.setOnAction(event -> {
            if(sudoku != null) {
                sudokuResolver.resolve(sudoku);
                update();
            }
        });
        resolveButton.setTranslateX(120);
        topPane.getChildren().add(importButton);
        topPane.getChildren().add(resolveButton);
        root.setTop(topPane);
    }

    private void loadScene(Stage primaryStage) {
        try {
            root = new BorderPane();
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

    public static void setInstance(Stage primaryStage, SudokuResolver sudokuResolver) {
        instance = new View(primaryStage, sudokuResolver);
    }

    public static View getInstance() {
        return instance;
    }

}
