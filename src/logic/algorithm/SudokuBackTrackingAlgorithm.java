package logic.algorithm;

import environment.Sudoku;
import logic.variable.Cell;
import logic.constraint.Constraint;
import logic.variable.Variable;
import view.View;

import java.util.ArrayList;
import java.util.List;

public class SudokuBackTrackingAlgorithm extends BackTrackingAlgorithm {

    private Sudoku sudoku;

    public SudokuBackTrackingAlgorithm(List<Variable> variables, List<Constraint> constraints, Sudoku sudoku) {
        super(variables, constraints);
        this.sudoku = sudoku;
    }

    @Override
    protected List<Variable> getNextVariables(Variable variable) {
        List<Variable> variables = new ArrayList<>();
        Cell cell = (Cell)variable;
        for(Variable variableTest : this.variables) {
            Cell cellTest = (Cell)variableTest;
            if((cell.getColumn() == cellTest.getColumn() && cellTest.getRow() == cell.getRow() + 1)
                    || (cell.getRow() == cellTest.getRow() && cellTest.getColumn() == cell.getColumn() + 1)) {
                variables.add(variableTest);
            }
        }
        return variables;
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

}
