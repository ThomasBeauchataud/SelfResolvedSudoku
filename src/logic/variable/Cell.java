package logic.variable;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Cell implements Variable {

    private List<Integer> possibilities;
    private int row;
    private int column;
    private boolean locked;

    public Cell(int possibility, int row, int column) {
        this.possibilities = new ArrayList<>();
        this.possibilities.add(possibility);
        this.row = row;
        this.column = column;
        this.locked = false;
    }

    public Cell(int possibility, int row, int column, boolean locked) {
        this.possibilities = new ArrayList<>();
        this.possibilities.add(possibility);
        this.row = row;
        this.column = column;
        this.locked = true;
    }

    public Cell(List<Integer> possibilities, int row, int column) {
        this.possibilities = possibilities;
        this.row = row;
        this.column = column;
        this.locked = false;
    }

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }

    public int getSquare() {
        return (row / 3 ) * 3 + (column / 3);
    }

    public boolean isLocked() {
        return locked;
    }

    public void setLocked(boolean locked) {
        this.locked = locked;
    }

    @Override
    public List<Object> getPossibilities() {
        return this.possibilities.stream().map(s -> (Object)s).collect(Collectors.toList());
    }

    @Override
    public Object getLastPossibility() {
        return this.possibilities.get(0);
    }

    public void removePossibility(Object possibility) {
        for(int i = 0 ; i < this.possibilities.size() ; ++i) {
            if(possibility == this.possibilities.get(i)) {
                this.possibilities.remove(i);
                break;
            }
        }
    }

    @Override
    public boolean equalsTo(Variable variable) {
        return ((Cell)variable).getRow() == this.row && ((Cell)variable).getColumn() == this.column;
    }

    @Override
    public void setUniquePossibility(Object possibility) {
        this.possibilities.clear();
        this.possibilities.add((int)possibility);
    }
}
