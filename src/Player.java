public class Player {
    private int value;
    private String name;
    private int wins;

    public Player(int id) {
        this.value = id;
        this.name = "Player " + id;
        this.wins = 0;
    }

    public int getValue() {
        return value;
    }

    public String getName() {
        return name;
    }

    public void changeName(String name) {
        this.name = name;
    }

    public int getWins() {
        return wins;
    }

    public void addWin() {
        wins++;
    }
}
