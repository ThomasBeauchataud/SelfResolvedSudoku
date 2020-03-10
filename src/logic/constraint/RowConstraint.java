package logic.constraint;

import logic.variable.Cell;
import logic.variable.Variable;

import java.util.ArrayList;
import java.util.List;

public class RowConstraint extends AbstractConstraint {

    public RowConstraint(int rowId) {
        super(rowId);
    }

    @Override
    public List<Variable> apply(List<Variable> variables) {
        List<Variable> modifiedVariables = new ArrayList<>();
        List<Cell> cells = new ArrayList<>();
        for(Variable variable : variables) {
            Cell cell = (Cell)variable;
            if(cell.getRow() == this.id) {
                cells.add(cell);
            }
        }
        for(int i = 0 ; i < cells.size() ; ++i) {
            if(cells.get(i).getPossibilities().size() == 1) {
                for(int k = 0 ; k < cells.size() ; ++k) {
                    Cell cell = cells.get(k);
                    if(k != i && cell.getPossibilities().size() > 1) {
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
            if(cell.getRow() == this.id && cell.getPossibilities().size() == 1
                    && variable.getLastPossibility() == cell.getLastPossibility()) {
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean hasOnSubject(Variable variable) {
        return ((Cell)variable).getRow() == this.id;
    }

}
