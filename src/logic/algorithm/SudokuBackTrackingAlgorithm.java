package logic.algorithm;

import environment.Cell;
import logic.constraint.Constraint;
import logic.variable.Variable;

import java.util.ArrayList;
import java.util.List;

public class SudokuBackTrackingAlgorithm extends BackTrackingAlgorithm {

    public SudokuBackTrackingAlgorithm(List<Variable> variables, List<Constraint> constraints) {
        super(variables, constraints);
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

}
