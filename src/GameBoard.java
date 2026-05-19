public class GameBoard {
    private String[][] board;
    private int rows;
    private int columns;
    private int winLength;

    public GameBoard(int rows, int columns, int winLength) {
        this.board = new String[rows][columns];
        this.rows = rows;
        this.columns = columns;
        this.winLength = winLength;
    }

    public String[][] getBoard() {
        return board;
    }

    public String getCell(int row, int column) {
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
        return board[row][column] != null;
    }

    public void occupy(int row, int column, String value) {
        this.board[row][column] = value;
    }

    public void changeDifficulty(int rows, int columns, int winLength) {
        this.board = new String[rows][columns];
        this.winLength = winLength;
        this.rows = rows;
        this.columns = columns;
    }

    public void reset() {
        this.board = new String[rows][columns];
    }
}
