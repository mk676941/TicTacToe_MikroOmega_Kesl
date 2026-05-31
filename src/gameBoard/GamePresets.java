package gameBoard;

/**
 * BoardConfig presets class
 * contains board config presets
 * @author Matej Kesl
 */
public class GamePresets {
    //presets
    public static BoardConfig DEFAULT = new BoardConfig(3, 3, 3, "DEFAULT - 3x3 board ; 3 symbol win line");
    public static BoardConfig EASY = new BoardConfig(4, 4, 3, "EASY - 4x4 board ; 3 symbol win line");
    public static BoardConfig MEDIUM = new BoardConfig(5, 5, 4, "MEDIUM - 5x5 board ; 4 symbol win line");
    public static BoardConfig HARD = new BoardConfig(8, 8, 6, "HARD - 8x8 board ; 6 symbol win line");
}