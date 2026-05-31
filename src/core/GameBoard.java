package core;

/**
 * GameBoard management class
 * used for creating amd managing a game board
 * @author Matej Kesl
 */
public class GameBoard {
    private String[][] board;
    private int rows;
    private int columns;
    private int winLength;

    //constructor
    public GameBoard(int rows, int columns, int winLength) {
        this.board = new String[rows][columns];
        this.rows = rows;
        this.columns = columns;
        this.winLength = winLength;
    }

    /**
     * method for checking is a space is occupied
     * @param row - game board row
     * @param column - game board column
     * @return boolean - true if a space is occupied
     *                 - false if a space isn't occupied
     */
    public boolean isOccupied(int row, int column) {
        return board[row][column] != null;
    }

    /**
     * method for occupying a space
     * @param row - game board row
     * @param column - game board column
     * @param value - value to put into a space
     */
    public void occupy(int row, int column, String value) {
        this.board[row][column] = value;
    }

    /**
     * method for resetting a game board
     */
    public void reset() {
        this.board = new String[rows][columns];
    }

    //getters
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
}