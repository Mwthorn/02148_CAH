package common.src.main.server;

public class Player {

    private String name;
    private int points;
    private int id;
    private boolean ready;

    public Player(String name, int id) {
        this.name = name;
        this.points = 0;
        this.id = id;
        ready = false;
    }

    public Player(Player player) {
        this.name = player.getName();
        this.points = player.getPoints();
        this.id = player.getId();
        this.ready = player.getReady();
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

    public boolean getReady() {
        return this.ready;
    }

    public void changeReady() {
    	if (this.ready == false){
    		this.ready = true;
    	} else {
    		this.ready = false;
    	}
    }

}
