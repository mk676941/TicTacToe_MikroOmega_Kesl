public class Game {
    private GameBoard gameBoard;
    private Player player1;
    private Player player2;
    private int currentPlayer;

    public Game(GameBoard gameBoard, Player player1, Player player2) {
        this.gameBoard = gameBoard;
        this.player1 = player1;
        this.player2 = player2;
        this.currentPlayer = player1.getId();
    }

    public void switchPlayer() {
        currentPlayer = (currentPlayer == player1.getId()) ? player2.getId() : player1.getId();
    }

    public int getCurrentPlayer() {
        return currentPlayer;
    }

    public void makeMove(int row, int column) {
        if (!gameBoard.isOccupied(row, column)) {
            gameBoard.occupy(row, column, currentPlayer);
        }
    }

    public boolean checkWin() {

    }

    public boolean checkHorizontally(int playerId) {
        int winLength = gameBoard.getWinLength();

        for (int i = 0; i < gameBoard.getRows(); i++) {
            int count = 0;

            for (int j = 0; j < gameBoard.getColumns(); j++) {
                if (gameBoard.getCell(i, j) == playerId) {
                    count++;
                    if (count >= winLength) {
                        return true;
                    }
                } else {
                    count = 0;
                }
            }
        }
        return false;
    }

    public boolean checkVertically(int playerId) {
        int winLength = gameBoard.getWinLength();

        for (int i = 0; i < gameBoard.getColumns(); i++) {
            int count = 0;

            for (int j = 0; j < gameBoard.getRows(); j++) {
                if (gameBoard.getCell(i, j) == playerId) {
                    count++;
                    if (count >= winLength) {
                        return true;
                    }
                } else {
                    count = 0;
                }
            }
        }
        return false;
    }

    public boolean checkDiagonally(int playerId) {
        int winLength = gameBoard.getWinLength();

        for (int i = 0; i < gameBoard.getRows(); i++) {
            int count = 0;

            for (int j = 0; j < gameBoard.getColumns(); j++) {
                if (gameBoard.getCell(i, j) == playerId) {
                    count++;
                    if (count >= winLength) {
                        return true;
                    }
                } else {
                    count = 0;
                }
            }
        }
        return false;
    }
}