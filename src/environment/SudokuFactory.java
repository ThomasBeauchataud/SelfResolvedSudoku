package environment;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Random;

/**
 * Create Sudoku from an import CSV file or from resources
 */
@SuppressWarnings("Duplicates")
public class SudokuFactory {

    private static SudokuFactory instance;

    private List<Sudoku> sudokuList;

    public SudokuFactory() {
        sudokuList = new ArrayList<>();
        importSudoku();
    }

    /**
     * Create a Sudoku from a CSV file
     * @param file File
     * @return Sudoku
     */
    public Sudoku createSudoku(File file) {
        if (file.getName().contains(".csv")) {
            return createSudokuFromCsv(file);
        }
        return null;
    }

    /**
     * Return a Sudoku from the list previously generated
     * @return Sudoku
     */
    public Sudoku generateSudoku() {
        return sudokuList.get(new Random().nextInt(sudokuList.size() - 1 + 1));
    }

    /**
     * Parse a CSV to create a Sudoku
     * @param file FIle
     * @return Sudoku
     */
    private Sudoku createSudokuFromCsv(File file) {
        try {
            BufferedReader csvReader = new BufferedReader(new FileReader(file));
            String row;
            int[][] finalData = null;
            int i = 0;
            while ((row = csvReader.readLine()) != null) {
                String[] line = row.split(",");
                int[] data = new int[line.length];
                for (int k = 0; k < line.length; k++) {
                    data[k] = Integer.parseInt(line[k]);
                }
                if (finalData == null) {
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

    private void importSudoku() {
        final File folder = new File(System.getProperty("user.dir") + "/resources");
        for (final File fileEntry : Objects.requireNonNull(folder.listFiles())) {
            if (!fileEntry.isDirectory() && fileEntry.getName().contains(".txt")) {
                try {
                    BufferedReader csvReader = new BufferedReader(new FileReader(fileEntry));
                    String row;
                    int[][] finalData = null;
                    int i = 0;
                    while ((row = csvReader.readLine()) != null) {
                        try {
                            String[] line = row.split(" ");
                            int[] data = new int[line.length];
                            for (int k = 0; k < line.length; k++) {
                                data[k] = Integer.parseInt(line[k]);
                            }
                            if (finalData == null) {
                                finalData = new int[data.length][];
                            }
                            finalData[i] = data;
                            i++;
                        } catch (Exception e) {
                            System.out.println("Sudoku imported from" + fileEntry.getName());
                        }
                    }
                    csvReader.close();
                    sudokuList.add(new Sudoku(finalData));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static SudokuFactory getInstance() {
        if (instance == null) {
            instance = new SudokuFactory();
        }
        return instance;
    }
}
