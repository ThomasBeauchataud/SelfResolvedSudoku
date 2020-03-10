package logic.constraint;

import logic.variable.Cell;
import logic.variable.Variable;

import java.util.ArrayList;
import java.util.List;

public class SquareConstraint extends AbstractConstraint {

    public SquareConstraint(int squareId) {
        super(squareId);
    }

    @Override
    public List<Variable> apply(List<Variable> variables) {
        List<Variable> modifiedVariables = new ArrayList<>();
        List<Cell> cells = new ArrayList<>();
        for(Variable variable : variables) {
            Cell cell = (Cell)variable;
            if(cell.getSquare() == this.id) {
                cells.add(cell);
            }
        }
        for(int i = 0 ; i < cells.size() ; ++i) {
            if(cells.get(i).getPossibilities().size() == 1) {
                for(int k = 0 ; k < cells.size() ; ++k) {
                    if(k != i) {
                        Cell cell = cells.get(k);
                        cell.removePossibility(cells.get(i).getLastPossibility());
                        modifiedVariables.add(cell);
                    }
                }
            }
        }
        return filterVariables(modifiedVariables);
    }

    @Override
    public boolean valid(Variable variable, List<Variable> variables) {
        for(Variable testVariable : variables) {
            Cell cell = (Cell)testVariable;
            if(cell.getSquare() == this.id && cell.getPossibilities().size() == 1
                    && variable.getLastPossibility() == cell.getLastPossibility()) {
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean hasOnSubject(Variable variable) {
        return ((Cell)variable).getSquare() == this.id;
    }

}
