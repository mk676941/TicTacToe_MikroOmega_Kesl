package gameBoard;

/**
 * GameBoard configuration class
 * used for configuring a game board
 * @author Matej Kesl
 */
public class BoardConfig {
    private int rows;
    private int columns;
    private int winLength;
    private String name;

    //constructor
    public BoardConfig(int rows, int columns, int winLength, String name) {
        this.rows = rows;
        this.columns = columns;
        this.winLength = winLength;
        this.name = name;
    }

    //getters
    public int getRows() {
        return rows;
    }

    public int getColumns() {
        return columns;
    }

    public int getWinLength() {
        return winLength;
    }

    public String getName() {
        return name;
    }

    //toString
    @Override
    public String toString() {
        return name;
    }
}