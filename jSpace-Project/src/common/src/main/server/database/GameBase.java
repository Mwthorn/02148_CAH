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

	public ArrayList<Game> getGames() {
		return this.games;
	}

	public int getGameId() {
		// Generate random ID
		return 0;
	}
	
    public Game getGamewithID(int id) {
        for (Game game : games) {
            if (game.getID() == id) {
                return game;
            }
        }
        return null;
    }

	public void addGame(Game game) {
		games.add(game);
	}

}
