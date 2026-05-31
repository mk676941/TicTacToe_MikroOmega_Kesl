package core;

import gameBoard.BoardConfig;
import gameConfig.ConfigManager;
import gameConfig.GameConfig;

import java.awt.*;
import java.util.ArrayList;

/**
 * game management class
 * used for creating and managing a game
 * contains methods for creating a game
 * contains methods for playing a game
 * @author Matej Kesl
 */
public class Game {
    private BoardConfig boardConfig;
    private GameConfig gameConfig;
    private GameBoard gameBoard;
    private Player player1;
    private Player player2;
    private Player currentPlayer;
    private int moves;

    private int lastRow;
    private int lastColumn;

    private ArrayList<int[]> winningCells;

    //core.Game constructor
    public Game(BoardConfig boardConfig, String name1, String name2, Color color1, Color color2, GameConfig gameConfig) {
        this.boardConfig = boardConfig;
        this.gameConfig = gameConfig;
        gameBoard = new GameBoard(boardConfig.getRows(), boardConfig.getColumns(), boardConfig.getWinLength());

        player1 = new Player("X", name1, color1);
        player2 = new Player("O", name2, color2);
        currentPlayer = randomPlayer();

        moves = 0;
        lastRow = -1;
        lastColumn = -1;
        winningCells = new ArrayList<>();
    }

    /**
     * method for picking a random player
     * @return core.Player - one of created players
     */
    public Player randomPlayer() {
        if (Math.random() < 0.5) {
            return player1;
        } else {
            return player2;
        }
    }

    /**
     * main method for playing the game
     * @param row - game board row
     * @param column - game board column
     * @return String - game status
     */
    public String play(int row, int column) {
        //invalid move
        if (!makeMove(row, column)) {
            return "Invalid move";
        }

        //win
        if (checkWin()) {
            if (currentPlayer.equals(player1)) {
                gameConfig.addP1Wins();
            } else {
                gameConfig.addP2Wins();
            }
            gameConfig.addGamesPlayed();
            ConfigManager.save(gameConfig);
            return "Win";
        }

        //draw
        if (moves == gameBoard.getRows() * gameBoard.getColumns()) {
            gameConfig.addDraws();
            gameConfig.addGamesPlayed();
            ConfigManager.save(gameConfig);
            return "Draw";
        }

        //continue
        switchPlayer();
        return "Continue";
    }

    //getters
    public GameBoard getGameBoard() {
        return gameBoard;
    }

    public ArrayList<int[]> getWinningCells() {
        return winningCells;
    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    /**
     * method for restarting the game
     * picks a new random player
     */
    public void restart() {
        gameBoard.reset();
        winningCells.clear();
        moves = 0;
        currentPlayer = randomPlayer();
        lastRow = -1;
        lastColumn = -1;
    }

    /**
     * method for switching the current player
     */
    public void switchPlayer() {
        currentPlayer = (currentPlayer == player1) ? player2 : player1;
    }

    /**
     * method for makeing a move and occupying a space
     * @param row - game board row
     * @param column - gmae board column
     * @return boolean - false if the space is not in bounds or occupied
     *                 - true if the space has been occupied
     */
    public boolean makeMove(int row, int column) {
        //not in bounds
        if (!isInBounds(row, column)) {
            return false;
        }

        //is occupied
        if (gameBoard.isOccupied(row, column)) {
            return false;
        } else {
            //occupying a space
            gameBoard.occupy(row, column, currentPlayer.getValue());

            this.lastRow = row;
            this.lastColumn = column;
            moves++;

            return true;
        }
    }

    /**
     * method for checking if the choosen space is in the bounds of the game board
     * @param row - game board row
     * @param column - game board column
     * @return boolean - true if the space is in bounds
     *                 - false if the space isn't in bounds
     */
    private boolean isInBounds(int row, int column) {
        return row >= 0 && row < gameBoard.getRows() && column >= 0 && column < gameBoard.getColumns();
    }

    /**
     * method for checking if there is a win line
     * @return boolean - true if a win line is found
     *                 - false if a win line isn't found
     */
    public boolean checkWin() {
        winningCells.clear();

        //horizontal check
        if (checkHorizontally(lastRow, lastColumn, currentPlayer) >= gameBoard.getWinLength()) {
            return true;
        } winningCells.clear();

        //vertical check
        if (checkVertically(lastRow, lastColumn, currentPlayer) >= gameBoard.getWinLength()) {
            return true;
        } winningCells.clear();

        //1st diagonal check
        if (checkDiagonally1(lastRow, lastColumn, currentPlayer) >= gameBoard.getWinLength()) {
            return true;
        } winningCells.clear();

        //2nd diagonal check
        if (checkDiagonally2(lastRow, lastColumn, currentPlayer) >= gameBoard.getWinLength()) {
            return true;
        } winningCells.clear();

        return false;
    }

    /**
     * method for checking horizontally
     * @param row - game board row
     * @param column - game board column
     * @param player - player to check
     * @return int - number of players occupied spaces after each other
     */
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

    /**
     * method for checking vertically
     * @param row - game board row
     * @param column - game board column
     * @param player - player to check
     * @return int - number of players occupied spaces after each other
     */
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

    /**
     * method for checking 1st diagonal
     * @param row - game board row
     * @param column - game board column
     * @param player - player to check
     * @return int - number of players occupied spaces after each other
     */
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

    /**
     * method for checking 2nd diagonal
     * @param row - game board row
     * @param column - game board column
     * @param player - player to check
     * @return int - number of players occupied spaces after each other
     */
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