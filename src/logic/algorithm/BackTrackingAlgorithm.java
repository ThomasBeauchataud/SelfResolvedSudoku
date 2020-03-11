package logic.algorithm;

import logic.constraint.Constraint;
import logic.variable.Variable;

import java.util.ArrayList;
import java.util.List;

/**
 * Abstract BackTracking Algorithm
 */
@SuppressWarnings("unused")
public abstract class BackTrackingAlgorithm implements Algorithm {

    protected List<Variable> variables;
    protected List<Constraint> constraints;

    public BackTrackingAlgorithm(List<Variable> variables, List<Constraint> constraints) {
        this.variables = variables;
        this.constraints = constraints;
    }

    /**
     * Run the algorithm
     */
    @Override
    public void run() {
        if(solve(this.variables.get(0))) {
            update();
        } else {
            System.err.println("Impossible to resolve the sudoku with back tracking algorithm");
        }
    }

    /**
     * Recursive loop of the BackTracking Algorithm
     * @param variable Variable
     * @return boolean
     */
    private boolean solve(Variable variable) {
        List<Variable> assignments = getNextVariables(variable);
        //Foreach variable possibility
        for (Object possibility : variable.getPossibilities()) {
            //We check the validity of the possibility
            if (isValidPossibility(variable, possibility) || variable.getPossibilities().size() == 1) {
                //We assign the possibility to the variable
                variable.setUniquePossibility(possibility);
                boolean result = true;
                // We check if affected variables can solve the problem with this possibility choice
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

    /**
     * Return true if a possibility is valid for a variable
     * @param variable Variable
     * @param possibility Object
     * @return boolean
     */
    protected abstract boolean isValidPossibility(Variable variable, Object possibility);

    /**
     * Return the constraint list associated to a variable
     * @param variable Variable
     * @return Constraint[]
     */
    protected List<Constraint> getAssociatedConstraints(Variable variable) {
        List<Constraint> constraints = new ArrayList<>();
        for(Constraint constraint : this.constraints) {
            if(constraint.hasOnSubject(variable)) {
                constraints.add(constraint);
            }
        }
        return constraints;
    }

    /**
     * Return next variables to solve
     * @param variable Variable
     * @return Variable[]
     */
    protected abstract List<Variable> getNextVariables(Variable variable);

}
