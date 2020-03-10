package logic.algorithm;

import logic.variable.Cell;
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
    public void update() {
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

    @Override
    protected boolean solutionComplete(List<Variable> variables) {
        for(Variable variable : variables) {
            if(variable.getPossibilities().size() > 1) {
                return false;
            }
        }
        return true;
    }

}
