package logic.constraint;

import logic.variable.Variable;

import java.util.List;

public interface Constraint {

    List<Variable> apply(List<Variable> variables);

    boolean valid(Variable variable, List<Variable> variables);

    boolean hasOnSubject(Variable variable);

    boolean equalsTo(Constraint constraint);

}
