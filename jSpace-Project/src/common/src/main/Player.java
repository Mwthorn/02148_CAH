package common.src.main;

public class Player {

    private String name;
    private int points;

    public Player(String name) {
        this.name = name;
        this.points = 0;
    }

    public int getPoints() {
        return this.points;
    }

    public String getName() {
        return this.name;
    }

    public void setPoints(int i) {
        this.points = i;
    }

    public void addPoints(int i) {
        this.points += i;
    }

}
