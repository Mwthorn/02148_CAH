package common.src.main;

import org.jspace.SequentialSpace;
import org.jspace.SpaceRepository;

import common.src.main.cards.BlackCard;
import common.src.main.cards.WhiteCard;

import java.util.ArrayList;

public class Game implements Runnable {

    private String gameName;
    private String password;
    private WhiteCard[] whiteCards;
    private BlackCard[] blackCards;
    private ArrayList<Player> players;
    private String status;
    // TODO: Add chat?
    // TODO: Add rounds?
    // TODO: Add host?
    // TODO: Add vote count?

    public Game(String gameName, WhiteCard[] whiteCards, BlackCard[] blackCards) {
        this.gameName = gameName;
        this.whiteCards = whiteCards;
        this.blackCards = blackCards;
        this.password = null;
    }

    public Game(String gameName, WhiteCard[] whiteCards, BlackCard[] blackCards, String password) {
        this.gameName = gameName;
        this.whiteCards = whiteCards;
        this.blackCards = blackCards;
        this.password = password;
    }

    public String getGameName() {
        return this.gameName;
    }

    public String getPassword() {
        return this.password;
    }

    public boolean hasPassword() {
        return this.password != null;
    }

    public ArrayList<Player> getPlayers() {
        return this.players;
    }

    public String getStatus() {
        return this.status;
    }

    public void addPointsTo(Player player, int i) {
        player.addPoints(i);
    }

    public void addPlayerToGame(Player player) {
        players.add(player);
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setPassword(String password) {
        this.password = password;
    }

	SpaceRepository repository = new SpaceRepository();
	SequentialSpace game = new SequentialSpace();

    public Game(SpaceRepository repository, int gameSlot) {
    	this.repository = repository;
    	this.repository.addGate("tcp://127.0.0.1:9001/?keep");
		this.repository.add("game"+1, game);
	}
    
	public void run() {
		
		
	}

}
