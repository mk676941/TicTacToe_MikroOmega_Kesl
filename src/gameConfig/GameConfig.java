package gameConfig;

import gameBoard.BoardConfig;

/**
 * game config classs
 * used for managing a game config
 * all data are loaded from this class
 * contains stats and settings management methods
 * @author Matej Kesl
 */
public class GameConfig{
    private BoardConfig board;
    private String player1Name;
    private String player2Name;
    private String color1;
    private String color2;

    public static final String defaultp1 = "Player 1";
    public static final String defaultp2 = "Player 2";
    public static final String defaultc1 = "#000000";
    public static final String defaultc2 = "#000000";

    private int gamesPlayed;
    private int draws;
    private int p1Wins;
    private int p2Wins;

    //constructor
    public GameConfig(BoardConfig board, String player1Name, String player2Name, String color1, String color2) {
        this.board = board;
        this.player1Name = player1Name;
        this.player2Name = player2Name;
        this.color1 = color1;
        this.color2 = color2;

        draws = 0;
        gamesPlayed = 0;
        p1Wins = 0;
        p2Wins = 0;
    }

    /**
     * method for reseting stats
     */
    public void resetStats() {
        draws = 0;
        gamesPlayed = 0;
        p1Wins = 0;
        p2Wins = 0;
    }

    //getters and setters
    public BoardConfig getBoard() {
        return board;
    }

    public void setBoard(BoardConfig board) {
        this.board = board;
    }

    public String getPlayer1Name() {
        return player1Name;
    }

    public void setPlayer1Name(String player1Name) {
        this.player1Name = player1Name;
    }

    public String getPlayer2Name() {
        return player2Name;
    }

    public void setPlayer2Name(String player2Name) {
        this.player2Name = player2Name;
    }

    public String getColor1() {
        return color1;
    }

    public void setColor1(String color1) {
        this.color1 = color1;
    }

    public String getColor2() {
        return color2;
    }

    public void setColor2(String color2) {
        this.color2 = color2;
    }

    public int getGamesPlayed() {
        return gamesPlayed;
    }

    public void addGamesPlayed() {
        gamesPlayed++;
    }

    public int getDraws() {
        return draws;
    }

    public void addDraws() {
        draws++;
    }

    public int getP1Wins() {
        return p1Wins;
    }

    public void addP1Wins() {
        p1Wins++;
    }

    public int getP2Wins() {
        return p2Wins;
    }

    public void addP2Wins() {
        p2Wins++;
    }
}
