package logic.algorithm;

import logic.constraint.Constraint;
import logic.variable.Variable;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Abstract AC3 Algorithm
 */
public abstract class AC3Algorithm implements Algorithm {

    protected List<Variable> variables;
    protected Queue<Constraint> agenda;
    protected List<Constraint> constraints;

    public AC3Algorithm(List<Variable> variables, List<Constraint> constraints) {
        this.variables = variables;
        this.constraints = constraints;
        agenda = new LinkedList<>();
    }

    /**
     * Run the AC3 algorithm
     */
    @Override
    public void run() {
        //Initialize agenda with all constraint
        agenda.addAll(constraints);
        while(agenda.size() > 0 && !solutionComplete(variables)) {
            //We get the first constraint
            Constraint constraint = agenda.poll();
            assert constraint != null;
            //We apply it and get modified variables
            List<Variable> modifiedVariables = constraint.apply(variables);
            //If there is modified variables
            if(modifiedVariables.size() > 0) {
                //We add the constraints affected by the previous one
                agenda.addAll(getAffectedConstraints(modifiedVariables, constraint));
            }
            update();
        }
    }

    /**
     * Return true if the solution is complete
     * @param variables Variable[]
     * @return boolean
     */
    protected abstract boolean solutionComplete(List<Variable> variables);

    /**
     * Return the list of constraint affected by the application of the previous constraint
     * @param variables Variable[]
     * @param lastConstraint Constraint
     * @return Constraint[]
     */
    private List<Constraint> getAffectedConstraints(List<Variable> variables, Constraint lastConstraint) {
        List<Constraint> constraints = new ArrayList<>();
        for(Variable variable : variables) {
            for(Constraint constraint : this.constraints) {
                if(constraint.hasOnSubject(variable)) {
                    constraints.add(constraint);
                }
            }
        }
        for(int i = 0 ; i < constraints.size() ; ++i) {
            for(int k = i + 1 ; k < constraints.size() ; ++k) {
                if(constraints.get(i).equalsTo(constraints.get(k)) || constraints.get(i).equalsTo(lastConstraint)) {
                    constraints.remove(k);
                    k--;
                }
            }
        }
        return constraints;
    }

}
