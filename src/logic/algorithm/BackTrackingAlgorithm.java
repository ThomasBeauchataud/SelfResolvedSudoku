package logic.algorithm;

import logic.constraint.Constraint;
import logic.variable.Cell;
import logic.variable.Variable;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("unused")
public abstract class BackTrackingAlgorithm implements Algorithm {

    protected List<Variable> variables;
    protected List<Constraint> constraints;

    public BackTrackingAlgorithm(List<Variable> variables, List<Constraint> constraints) {
        this.variables = variables;
        this.constraints = constraints;
    }

    @Override
    public void run() {
        if(solve(this.variables.get(0))) {
            update();
        } else {
            System.err.println("Impossible to resolve the sudoku with back tracking algorithm");
        }
    }

    private boolean solve(Variable variable) {
        List<Variable> assignments = getNextVariables(variable);
        for (Object possibility : variable.getPossibilities()) {
            if (isValidPossibility(variable, possibility) || variable.getPossibilities().size() == 1) {
                variable.setUniquePossibility(possibility);
                boolean result = true;
                for(Variable nextVariable : getNextVariables(variable)) {
                    if(!solve(nextVariable)) {
                        result = false;
                    }
                }
                if(result) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean isValidPossibility(Variable variable, Object possibility) {
        for(Constraint constraint : getAssociatedConstraints(variable)) {
            Cell cell = (Cell)variable;
            Cell variableTemp = new Cell((Integer) possibility, cell.getRow(), cell.getColumn());
            if(!constraint.valid(variableTemp, this.variables)) {
                    return false;
            }
        }
        return true;
    }

    private List<Constraint> getAssociatedConstraints(Variable variable) {
        List<Constraint> constraints = new ArrayList<>();
        for(Constraint constraint : this.constraints) {
            if(constraint.hasOnSubject(variable)) {
                constraints.add(constraint);
            }
        }
        return constraints;
    }

    protected abstract List<Variable> getNextVariables(Variable variable);

}
