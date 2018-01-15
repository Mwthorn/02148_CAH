package common.src.main.server;

import common.src.main.server.utilities.WhiteCard;

import java.util.ArrayList;

public class Player {

    private String name;
    private int points;
    private int id;
    private boolean ready;
    private GameSlot gameSlot;
    private WhiteCard[] whiteCards;
    private ArrayList<WhiteCard> pickedCards;
    private boolean[] hasPicked;

    public Player(String name, int id) {
        this.name = name;
        this.points = 0;
        this.id = id;
        this.whiteCards = new WhiteCard[10];
        
        ready = false;
        
        // TODO: Find a way to add the correct gameslot.
        this.gameSlot = new GameSlot(0, name, true);
    }

    public Player(Player player) {
        this.name = player.getName();
        this.points = player.getPoints();
        this.id = player.getId();
        this.ready = player.getReady();
        this.whiteCards = new WhiteCard[10];
    }

    public void setWhiteCards(WhiteCard[] whiteCards) {
        this.whiteCards = whiteCards;
    }

    public void setWhiteCard(WhiteCard whiteCard,int i) {
        this.whiteCards[i] = whiteCard;
    }

    public void removeWhiteCard(int index) {
        this.whiteCards[index] = null;
    }

    public WhiteCard[] getWhiteCards() {
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

    public boolean hasPickedCard(int i) {
        return (pickedCards.size()-1 >= i);
    }

    public void resetPickedCards() {
        this.pickedCards = new ArrayList<>();
        this.hasPicked = new boolean[10];
    }

    public void chooseWhiteCard(int i) {
        WhiteCard card = whiteCards[i];
        this.pickedCards.add(card);
        whiteCards[i] = null;
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

	public void setGameSlot(int availableSlot) {
		gameSlot.setSlot(availableSlot);
	}

    public ArrayList<WhiteCard> getPickedCards() {
        return this.pickedCards;
    }

    public boolean hasPickedWhiteCard(int i) {
        return hasPicked[i];
    }
}
