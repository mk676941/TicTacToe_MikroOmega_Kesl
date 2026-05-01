public class Player {
    private int id;
    private String name;
    private int wins;

    public Player(int id) {
        this.id = id;
        this.name = "Player " + id;
        this.wins = 0;
    }

    public int getId() {
        return id;
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
