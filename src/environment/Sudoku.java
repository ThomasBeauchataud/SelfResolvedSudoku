package environment;

/**
 * The Sudoku object
 */
public class Sudoku {

    private int[][] data;

    public Sudoku(int[][] data) {
        this.data = data;
    }

    public int[][] getData() {
        return data;
    }

    public int getSize() {
        return data.length;
    }

}
