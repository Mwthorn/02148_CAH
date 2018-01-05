package common.src.main;

public class Player {

    private String name;
    private int points;
    private int id;

    public Player(String name, int id) {
        this.name = name;
        this.points = 0;
        this.id = id;
    }

    public int getPoints() {
        return this.points;
    }

    public String getName() {
        return this.name;
    }

    public int getId() {
        return this.id;
    }

    public void setPoints(int i) {
        this.points = i;
    }

    public void addPoints(int i) {
        this.points += i;
    }

}
