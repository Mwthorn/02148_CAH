package common.src.main.server.database;

import common.src.main.server.Player;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Random;

public class PlayerBase {

    private ArrayList<Player> players = new ArrayList<>();

    public PlayerBase() {
    }

    public void addPlayer(Player player) {
        players.add(player);
    }

    public int getUniqueId() {
        boolean needsUnique = true;
        int n = 0;
        while(needsUnique) {
            Random rand = new Random();
            n = rand.nextInt(99999) + 10000;
            needsUnique = false;
            for (Player player : players) {
                if (player.getId() == n) {
                    needsUnique = true;
                }
            }
        }
        return n;
    }

    public Player getPlayerwithID(int id) {
        for (Player player : players) {
            if (player.getId() == id) {
                return player;
            }
        }
        return null;
    }

    public int getSize() {
        return players.size();
    }
    
    public int getPlayerNumber(Player player) {
    	return players.indexOf(player);
    }
    
    public ArrayList<Player> getPlayers(){
    	return players;
    }
    
    public Player getName(String name){
    	for (Player player : players) {
            if (Objects.equals(player.getName(), name)) {
                return player;
            }
        }
        return null;
    }

	public int getPlayerID(int i) {
		return players.get(i).getId();
	}
}
