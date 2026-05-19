import javax.swing.*;
import java.util.ArrayList;
import java.util.Arrays;

public class Game {
    private BoardConfig boardConfig;
    private GameBoard gameBoard;
    private Player player1;
    private Player player2;
    private Player currentPlayer;
    private int moves;

    private int lastRow;
    private int lastColumn;

    private ArrayList<int[]> winningCells;

    public Game(BoardConfig boardConfig) {
        this.boardConfig = boardConfig;
        gameBoard = new GameBoard(boardConfig.getRows(), boardConfig.getColumns(), boardConfig.getWinLength());
        player1 = new Player("X", "Player 1");
        player2 = new Player("O", "Player 2");
        currentPlayer = randomPlayer();
        moves = 0;
        lastRow = -1;
        lastColumn = -1;
        winningCells = new ArrayList<>();
    }

    public Player randomPlayer() {
        if (Math.random() < 0.5) {
            return player1;
        } else {
            return player2;
        }
    }

    public String play(int row, int column) {
        if (!makeMove(row, column)) {
            return "Invalid move";
        }

        if (checkWin()) {
            currentPlayer.addWin();
            return "Win";
        }

        if (moves == gameBoard.getRows() * gameBoard.getColumns()) {
            return "Draw";
        }

        switchPlayer();
        return "Continue";
    }

    public GameBoard getGameBoard() {
        return gameBoard;
    }

    public ArrayList<int[]> getWinningCells() {
        return winningCells;
    }

    public void setMoves(int moves) {
        this.moves = moves;
    }

    public void restart() {
        gameBoard.reset();
        winningCells.clear();
        moves = 0;
        currentPlayer = randomPlayer();
        lastRow = -1;
        lastColumn = -1;
    }

    public void switchPlayer() {
        currentPlayer = (currentPlayer == player1) ? player2 : player1;
    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    public boolean makeMove(int row, int column) {
        if (!isInBounds(row, column)) {
            return false;
        }

        if (gameBoard.isOccupied(row, column)) {
            return false;
        } else {
            gameBoard.occupy(row, column, currentPlayer.getValue());

            this.lastRow = row;
            this.lastColumn = column;

            moves++;

            return true;
        }
    }

    private boolean isInBounds(int row, int column) {
        return row >= 0 && row < gameBoard.getRows() && column >= 0 && column < gameBoard.getColumns();
    }

    public boolean checkWin() {
        winningCells.clear();

        if (checkHorizontally(lastRow, lastColumn, currentPlayer) >= gameBoard.getWinLength()) {
            return true;
        } winningCells.clear();

        if (checkVertically(lastRow, lastColumn, currentPlayer) >= gameBoard.getWinLength()) {
            return true;
        } winningCells.clear();

        if (checkDiagonally1(lastRow, lastColumn, currentPlayer) >= gameBoard.getWinLength()) {
            return true;
        } winningCells.clear();

        if (checkDiagonally2(lastRow, lastColumn, currentPlayer) >= gameBoard.getWinLength()) {
            return true;
        } winningCells.clear();

        return false;
    }

    private int checkHorizontally(int row, int column, Player player) {
        int count = 1;
        winningCells.add(new int[]{row, column});

        //right check
        int c = column + 1;
        while (c <gameBoard.getColumns() && player.getValue().equals(gameBoard.getCell(row, c))) {
            count++;
            winningCells.add(new int[]{row, c});
            c++;
        }

        //left check
        c = column - 1;
        while (c >= 0 && player.getValue().equals(gameBoard.getCell(row, c))) {
            count++;
            winningCells.add(new int[]{row, c});
            c--;
        }

       return count;
    }

    private int checkVertically(int row, int column, Player player) {
        int count = 1;
        winningCells.add(new int[]{row, column});

        //down check
        int r = row + 1;
        while (r<gameBoard.getRows() && player.getValue().equals(gameBoard.getCell(r, column))) {
            count++;
            winningCells.add(new int[]{r, column});
            r++;
        }

        //up check
        r = row - 1;
        while (r >= 0 && player.getValue().equals(gameBoard.getCell(r, column))) {
            count++;
            winningCells.add(new int[]{r, column});
            r--;
        }

        return count;
    }

   private int checkDiagonally1(int row, int column, Player player) {
       int count = 1;
       winningCells.add(new int[]{row, column});

       //down right check
       int r = row + 1;
       int c = column + 1;
       while (r<gameBoard.getRows() && c < gameBoard.getColumns() && player.getValue().equals(gameBoard.getCell(r, c))) {
           count++;
           winningCells.add(new int[]{r, c});
           r++;
           c++;
       }

       //up left check
       r = row - 1;
       c = column - 1;
       while (r >= 0 && c >= 0 && player.getValue().equals(gameBoard.getCell(r, c))) {
           count++;
           winningCells.add(new int[]{r, c});
           r--;
           c--;
       }

       return count;
   }

    private int checkDiagonally2(int row, int column, Player player) {
        int count = 1;
        winningCells.add(new int[]{row, column});

        //down left check
        int r = row + 1;
        int c = column - 1;
        while (r<gameBoard.getRows() && c >= 0 && player.getValue().equals(gameBoard.getCell(r, c))) {
            count++;
            winningCells.add(new int[]{r, c});
            r++;
            c--;
        }

        //up right check
        r = row - 1;
        c = column + 1;
        while (r >= 0 && c < gameBoard.getColumns() && player.getValue().equals(gameBoard.getCell(r, c))) {
            count++;
            winningCells.add(new int[]{r, c});
            r--;
            c++;
        }

        return count;
    }
}