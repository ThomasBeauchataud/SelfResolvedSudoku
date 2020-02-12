package logic.constraint;

import logic.variable.Variable;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractConstraint implements Constraint {

    protected int id;

    protected AbstractConstraint(int id) {
        this.id = id;
    }

    public int getId() {
        return this.id;
    }

    @Override
    public boolean equalsTo(Constraint constraint) {
            return constraint.getClass() == this.getClass() && ((AbstractConstraint)constraint).getId() == this.id;
    }

    protected List<Variable> filterVariables(List<Variable> variables) {
        List<Variable> modifiedVariables = new ArrayList<>();
        for(Variable variable : variables) {
            boolean containing = false;
            for(Variable modifiedVariable : modifiedVariables) {
                if(variable.equalsTo(modifiedVariable)) {
                    containing = true;
                }
            }
            if(!containing) {
                modifiedVariables.add(variable);
            }
        }
        return modifiedVariables;
    }
}
