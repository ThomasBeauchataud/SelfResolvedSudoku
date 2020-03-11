package logic.constraint;

import logic.variable.Variable;

import java.util.List;

/**
 * A constraint for algorithms
 */
public interface Constraint {

    /**
     * Apply a constraint and return affected variables
     * @param variables Variable
     * @return Variable[]
     */
    List<Variable> apply(List<Variable> variables);

    /**
     * Return true if a variable validate the constraint
     * @param variable Variable
     * @param variables Variable[] (the environment)
     * @return boolean
     */
    boolean valid(Variable variable, List<Variable> variables);

    /**
     * Return true if the constraint affect the variable
     * @param variable Variable
     * @return boolean
     */
    boolean hasOnSubject(Variable variable);

    /**
     * Return true if two constraints are equals
     * @param constraint Constraint
     * @return boolean
     */
    boolean equalsTo(Constraint constraint);

}
