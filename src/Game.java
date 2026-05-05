import javax.swing.*;
import java.util.ArrayList;
import java.util.Arrays;

public class Game {
    private GameBoard gameBoard;
    private Player player1;
    private Player player2;
    private Player currentPlayer;
    private int moves;

    private int lastRow;
    private int lastColumn;

    public Game() {
        this.gameBoard = new GameBoard(3, 3, 3);
        this.player1 = new Player(1);
        this.player2 = new Player(2);
        this.currentPlayer = player1;
        this.moves = 0;
        this.lastRow = -1;
        this.lastColumn = -1;
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

    public void restart() {
       gameBoard.reset();
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
        if (checkHorizontally(lastRow, lastColumn, currentPlayer) >= gameBoard.getWinLength()) return true;
        if (checkVertically(lastRow, lastColumn, currentPlayer) >= gameBoard.getWinLength()) return true;
        if (checkDiagonally1(lastRow, lastColumn, currentPlayer) >= gameBoard.getWinLength()) return true;
        if (checkDiagonally2(lastRow, lastColumn, currentPlayer) >= gameBoard.getWinLength()) return true;

        return false;
    }

    private int checkHorizontally(int row, int column, Player player) {
        int count = 1;

        //right check
        int c = column + 1;
        while (c <gameBoard.getColumns() && gameBoard.getCell(row, c) == player.getValue()) {
            count++;
            c++;
        }

        //left check
        c = column - 1;
        while (c >= 0 && gameBoard.getCell(row, c) == player.getValue()) {
            count++;
            c--;
        }

        return count;
    }

    private int checkVertically(int row, int column, Player player) {
        int count = 1;

        //down check
        int r = row + 1;
        while (r<gameBoard.getRows() && gameBoard.getCell(r, column) == player.getValue()) {
            count++;
            r++;
        }

        //up check
        r = row - 1;
        while (r >= 0 && gameBoard.getCell(r, column) == player.getValue()) {
            count++;
            r--;
        }

        return count;
    }

   private int checkDiagonally1(int row, int column, Player player) {
       int count = 1;

       //down right check
       int r = row + 1;
       int c = column + 1;
       while (r<gameBoard.getRows() && c < gameBoard.getColumns() && gameBoard.getCell(r, c) == player.getValue()) {
           count++;
           r++;
           c++;
       }

       //up left check
       r = row - 1;
       c = column - 1;
       while (r >= 0 && c >= 0 && gameBoard.getCell(r, c) == player.getValue()) {
           count++;
           r--;
           c--;
       }

       return count;
   }

    private int checkDiagonally2(int row, int column, Player player) {
        int count = 1;

        //down left check
        int r = row + 1;
        int c = column - 1;
        while (r<gameBoard.getRows() && c >= 0 && gameBoard.getCell(r, c) == player.getValue()) {
            count++;
            r++;
            c--;
        }

        //up right check
        r = row - 1;
        c = column + 1;
        while (r >= 0 && c < gameBoard.getColumns() && gameBoard.getCell(r, c) == player.getValue()) {
            count++;
            r--;
            c++;
        }

        return count;
    }
}