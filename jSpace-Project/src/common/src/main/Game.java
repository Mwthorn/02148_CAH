package common.src.main;

import org.jspace.SequentialSpace;
import org.jspace.SpaceRepository;

import common.src.main.cards.BlackCard;
import common.src.main.cards.WhiteCard;

public class Game implements Runnable {
	SpaceRepository repository = new SpaceRepository();
	SequentialSpace game = new SequentialSpace();
    // Players in game
    // Password
    // Cards
	
	private String gameName;
    private String password;
    private WhiteCard[] whiteCards;
    private BlackCard[] blackCards;
	
    public Game(SpaceRepository repository, int gameSlot) {
    	this.repository = repository;
    	this.repository.addGate("tcp://127.0.0.1:9001/?keep");
		this.repository.add("game"+1, game);
	}
    
	public void run() {
		
		
	}

}
