public class BoardConfig {
    private int rows;
    private int columns;
    private int winLength;

    public BoardConfig(int rows, int columns, int winLength) {
        this.rows = rows;
        this.columns = columns;
        this.winLength = winLength;
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
