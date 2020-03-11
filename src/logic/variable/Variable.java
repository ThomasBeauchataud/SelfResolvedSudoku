package logic.variable;

import java.util.List;

/**
 * An algorithm variable
 */
public interface Variable {

    /**
     * Return the list of possibilities of a variable
     * If the size of the array is equals to 1, the variable has a unique possibility
     * @return Object[]
     */
    List<Object> getPossibilities();

    /**
     * Return true if a variable is equals to an other
     * @param variable Variable
     * @return boolean
     */
    boolean equalsTo(Variable variable);

    /**
     * Set the unique possibility of a variable
     * @param possibility Object
     */
    void setUniquePossibility(Object possibility);

    /**
     * Return the unique possibility
     * @return Object
     */
    Object getLastPossibility();

    /**
     * Remove a possibility from the variable
     * @param possibility Object
     */
    void removePossibility(Object possibility);

}
