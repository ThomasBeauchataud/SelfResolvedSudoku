package logic.algorithm;

import environment.Cell;
import environment.Sudoku;
import logic.constraint.Constraint;
import logic.variable.Variable;
import view.View;

import java.util.List;

public class SudokuAC3Algorithm extends AC3Algorithm {

    private Sudoku sudoku;

    public SudokuAC3Algorithm(List<Variable> variables, List<Constraint> constraints, Sudoku sudoku) {
        super(variables, constraints);
        this.sudoku = sudoku;
    }

    @Override
    protected void update() {
        boolean updated = false;
        for(Variable variable : this.variables) {
            Cell cell = (Cell)variable;
            if(sudoku.getData()[cell.getRow()][cell.getColumn()] == 0 && cell.getPossibilities().size() == 1) {
                sudoku.getData()[cell.getRow()][cell.getColumn()] = (int)cell.getLastPossibility();
                updated = true;
            }
        }
        if(updated) {
            View.getInstance().update();
        }
    }

}
