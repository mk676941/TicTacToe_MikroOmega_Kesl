public class GameBoard {
    private int[][] board;
    private int rows;
    private int columns;
    private int winLength;

    public GameBoard(int rows, int columns, int winLength) {
        this.board = new int[rows][columns];
        this.rows = rows;
        this.columns = columns;
        this.winLength = winLength;
    }

    public int[][] getBoard() {
        return board;
    }

    public int getCell(int row, int column) {
        return board[row][column];
    }

    public int getRows() {
        return rows;
    }

    public int getColumns() {
        return columns;
    }

    public int getWinLength() {
        return winLength;
    }

    public boolean isOccupied(int row, int column) {
        return board[row][column] != 0;
    }

    public void occupy(int row, int column, int value) {
        this.board[row][column] = value;
    }

    public void changeDifficulty(int rows, int columns, int winLength) {
        this.board = new int[rows][columns];
        this.winLength = winLength;
        this.rows = rows;
        this.columns = columns;
    }
}
