import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;

public class Main {

    private static String[][] matrix;
    private static boolean[][] takenCells;
    private static Queue<Cell> cells = new ArrayDeque<>();

    public static void main(String[] args) throws IOException {

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int size = Integer.parseInt(reader.readLine());
        matrix = new String[size][size];
        takenCells = new boolean[size][size];
        fillMatrix(reader);
        String startingPosition = getStartingPosition();
        int startingRow = Integer.parseInt(startingPosition.split(" ")[0]);
        int startingCol = Integer.parseInt(startingPosition.split(" ")[1]);
        insertMoves(startingRow, startingCol);
        printResult();

    }


    private static void fillMatrix(BufferedReader reader) throws IOException {
        for (int i = 0; i < matrix.length; i++) {
            String[] arrayLine = reader.readLine().split("");
            matrix[i] = arrayLine;
        }
    }

    private static String getStartingPosition() {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if (matrix[i][j].equals("*")) {
                    return i + " " + j;
                }
            }
        }
        return "";
    }

    private static void insertMoves(int startingRow, int startingCol) {
        cells.add(new Cell(startingRow, startingCol, 0));
        while (cells.size() > 0) {
            Cell cell = cells.poll();
            int row = cell.getRow();
            int col = cell.getCol();
            int moves = cell.getMoves();
            if (row - 1 >= 0 && !takenCells[row - 1][col] && !matrix[row - 1][col].equals("x") && !matrix[row - 1][col].equals("*")) {
                matrix[row - 1][col] = String.valueOf(moves + 1);
                takenCells[row - 1][col] = true;
                cells.add(new Cell(row - 1, col, moves + 1));
            }
            if (col + 1 < matrix[0].length && !takenCells[row][col + 1] && !matrix[row][col + 1].equals("x") && !matrix[row][col + 1].equals("*")) {
                matrix[row][col + 1] = String.valueOf(moves + 1);
                takenCells[row][col + 1] = true;
                cells.add(new Cell(row, col + 1, moves + 1));
            }
            if (row + 1 < matrix.length && !takenCells[row + 1][col] && !matrix[row + 1][col].equals("x") && !matrix[row + 1][col].equals("*")) {
                matrix[row + 1][col] = String.valueOf(moves + 1);
                takenCells[row + 1][col] = true;
                cells.add(new Cell(row + 1, col, moves + 1));
            }
            if (col - 1 >= 0 && !takenCells[row][col - 1] && !matrix[row][col - 1].equals("x") && !matrix[row][col - 1].equals("*")) {
                matrix[row][col - 1] = String.valueOf(moves + 1);
                takenCells[row][col - 1] = true;
                cells.add(new Cell(row, col - 1, moves + 1));
            }
        }
    }

    private static void printResult() {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if (matrix[i][j].equals("0")) {
                    System.out.print('u');
                } else {
                    System.out.print(matrix[i][j]);
                }
            }
            System.out.println();
        }
    }

}
