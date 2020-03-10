package logic.algorithm;

import logic.constraint.Constraint;
import logic.variable.Variable;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public abstract class AC3Algorithm implements Algorithm {

    protected List<Variable> variables;
    protected Queue<Constraint> agenda;
    protected List<Constraint> constraints;

    public AC3Algorithm(List<Variable> variables, List<Constraint> constraints) {
        this.variables = variables;
        this.constraints = constraints;
        agenda = new LinkedList<>();
    }

    @Override
    public void run() {
        agenda.addAll(constraints);
        while(agenda.size() > 0 && !solutionComplete(variables)) {
            Constraint constraint = agenda.poll();
            assert constraint != null;
            List<Variable> modifiedVariables = constraint.apply(variables);
            if(modifiedVariables.size() > 0) {
                agenda.addAll(getAffectedConstraints(modifiedVariables, constraint));
            }
            update();
        }
    }

    protected abstract void update();

    protected abstract boolean solutionComplete(List<Variable> variables);

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
