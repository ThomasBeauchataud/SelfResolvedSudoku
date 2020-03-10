package logic;

import environment.Sudoku;
import logic.algorithm.Algorithm;
import logic.algorithm.SudokuAC3Algorithm;
import logic.algorithm.SudokuBackTrackingAlgorithm;
import logic.constraint.*;
import logic.variable.Variable;

import java.util.List;

public class SudokuResolver {

    private SudokuAlgorithmFactory sudokuAlgorithmsFactory;

    public SudokuResolver() {
        sudokuAlgorithmsFactory = SudokuAlgorithmFactory.getInstance();
    }

    public void resolve(Sudoku sudoku) {
        List<Constraint> constraints = sudokuAlgorithmsFactory.createConstraints(sudoku);
        List<Variable> variables = sudokuAlgorithmsFactory.createVariables(sudoku);
        Algorithm algorithm = new SudokuAC3Algorithm(variables, constraints, sudoku);
        //Algorithm algorithm = new SudokuBackTrackingAlgorithm(variables, constraints);
        algorithm.run();
    }

}
