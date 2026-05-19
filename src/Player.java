public class Player {
    private String value;
    private String name;
    private int wins;

    public Player(String id, String name) {
        this.value = id;
        this.name = name;
        this.wins = 0;
    }

    public String getValue() {
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
