package common.src.main.server.database;

import common.src.main.server.Game;

import java.util.ArrayList;

public class GameBase {
	private ArrayList<Game> games = new ArrayList<>();
	
	public GameBase(){
		
	}

	public int getGameSlot() {
		// Return an available game slot.
		return 0;
	}

	public int getGameId() {
		// Generate random ID
		return 0;
	}

	public void addGame(Game game) {
		games.add(game);
	}

}
