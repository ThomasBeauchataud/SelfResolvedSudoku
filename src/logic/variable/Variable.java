package logic.variable;

import java.util.List;

public interface Variable {

    List<Object> getPossibilities();

    boolean equalsTo(Variable variable);

    void setUniquePossibility(Object possibility);

    Object getLastPossibility();

    void removePossibility(Object possibility);

}
