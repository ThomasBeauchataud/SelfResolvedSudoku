package environment;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public class SudokuFactory {

    private static SudokuFactory instance;

    public Sudoku createSudoku(File file) {
        if(file.getName().contains(".csv")) {
            return createSudokuFromCsv(file);
        }
        return null;
    }

    private Sudoku createSudokuFromCsv(File file) {
        try {
            BufferedReader csvReader = new BufferedReader(new FileReader(file));
            String row;
            int[][] finalData = null;
            int i = 0;
            while ((row = csvReader.readLine()) != null) {
                String[] line = row.split(",");
                int[] data = new int[line.length];
                for(int k = 0 ; k < line.length ; k++) {
                    data[k] = Integer.parseInt(line[k]);
                }
                if(finalData == null) {
                    finalData = new int[data.length][];
                }
                finalData[i] = data;
                i++;
            }
            csvReader.close();
            return new Sudoku(finalData);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static SudokuFactory getInstance() {
        if(instance == null) {
            instance = new SudokuFactory();
        }
        return instance;
    }
}
