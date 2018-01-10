package common.src.main.server;

import common.src.main.server.utilities.WhiteCard;

import java.util.ArrayList;

public class Player {

    private String name;
    private int points;
    private int id;
    private boolean ready;
    private GameSlot gameSlot;
    private ArrayList<WhiteCard> whiteCards;

    public Player(String name, int id) {
        this.name = name;
        this.points = 0;
        this.id = id;
        
        ready = false;
        
        // TODO: Find a way to add the correct gameslot.
        this.gameSlot = new GameSlot(0, name, true);
    }

    public Player(Player player) {
        this.name = player.getName();
        this.points = player.getPoints();
        this.id = player.getId();
        this.ready = player.getReady();
    }

    public void setWhiteCards(ArrayList<WhiteCard> whiteCards) {
        this.whiteCards = whiteCards;
    }

    public void addWhiteCard(WhiteCard whiteCard) {
        this.whiteCards.add(whiteCard);
    }

    public void removeWhiteCard(int index) {
        this.whiteCards.remove(index);
    }

    public ArrayList<WhiteCard> getWhiteCards() {
        return this.whiteCards;
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
