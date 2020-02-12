package environment;

import com.github.ffcfalcos.smartagent.environment.Environment;

public class Sudoku implements Environment {

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

    @Override
    public void updateView() {

    }

    @Override
    public boolean equalsTo(Environment environment) {
        return false;
    }

    @Override
    public Environment copy() {
        return null;
    }
}
