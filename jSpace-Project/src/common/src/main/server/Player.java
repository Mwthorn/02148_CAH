package common.src.main.server;

public class Player {

    private String name;
    private int points;
    private int id;
    private boolean ready;
    private GameSlot gameSlot;

    public Player(String name, int id) {
        this.name = name;
        this.points = 0;
        this.id = id;
        this.gameSlot = new GameSlot(0,name);
        
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
    	this.ready = !this.ready;
    	gameSlot.setReady(this.ready);
    }

	public GameSlot getGameSlot() {
		return this.gameSlot;
	}

}
