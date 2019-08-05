public class Cell {

    private int row;
    private int col;
    private int moves;

    public Cell(int row, int col, int moves) {
        this.row = row;
        this.col = col;
        this.moves = moves;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    public int getMoves() {
        return moves;
    }
}
