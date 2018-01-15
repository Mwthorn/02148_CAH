package common.src.main.server.database;

import common.src.main.server.Game;

import java.util.ArrayList;
import java.util.Random;

public class GameBase {
	private ArrayList<Game> games = new ArrayList<>();
	
	public GameBase(){
		
	}

	public int getUniqueID() {
		// Return an available game slot.
		boolean needsUnique = true;
		int n = 0;
		while(needsUnique) {
			Random rand = new Random();
			n = rand.nextInt(99999) + 10000;
			needsUnique = false;
			for (Game game : games) {
				if (game.getID() == n) {
					needsUnique = true;
				}
			}
		}
		return n;
	}

	public ArrayList<Game> getGames() {
		return this.games;
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

	public void removeGame(Game game) {
		games.remove(game);
	}

}
