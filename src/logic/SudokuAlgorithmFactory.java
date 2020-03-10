package logic;

import logic.variable.Cell;
import environment.Sudoku;
import logic.constraint.ColumnConstraint;
import logic.constraint.Constraint;
import logic.constraint.RowConstraint;
import logic.constraint.SquareConstraint;
import logic.variable.Variable;

import java.util.ArrayList;
import java.util.List;

public class SudokuAlgorithmFactory {

    private static SudokuAlgorithmFactory instance;

    public List<Constraint> createConstraints(Sudoku sudoku) {
        List<Constraint> constraints = new ArrayList<>();
        for(int i = 0 ; i < sudoku.getSize() ; ++i) {
            constraints.add(new RowConstraint(i));
            constraints.add(new SquareConstraint(i));
            constraints.add(new ColumnConstraint(i));
        }
        return constraints;
    }

    public List<Variable> createVariables(Sudoku sudoku) {
        List<Variable> variables = new ArrayList<>();
        for(int i = 0 ; i < sudoku.getSize() ; ++i) {
            for(int k = 0 ; k < sudoku.getSize() ; ++k) {
                if(sudoku.getData()[k][i] == 0) {
                    variables.add(new Cell(getPossibilities(sudoku), k, i));
                } else {
                    variables.add(new Cell(sudoku.getData()[k][i], k, i));
                }
            }
        }
        return variables;
    }

    private List<Integer> getPossibilities(Sudoku sudoku) {
        List<Integer> possibilities = new ArrayList<>();
        for(int i = 1 ; i <= sudoku.getSize() ; ++i) {
            possibilities.add(i);
        }
        return possibilities;
    }

    public static SudokuAlgorithmFactory getInstance() {
        if(instance == null) {
            instance = new SudokuAlgorithmFactory();
        }
        return instance;
    }
}
