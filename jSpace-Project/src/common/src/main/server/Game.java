package common.src.main.server;

import common.src.main.server.database.PlayerBase;
import common.src.main.server.utilities.BlackCard;
import common.src.main.server.utilities.WhiteCard;

import org.jspace.ActualField;
import org.jspace.FormalField;
import org.jspace.SequentialSpace;
import org.jspace.SpaceRepository;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Objects;

public class Game implements Runnable {

    private String gameName;
    private String password;
    private ArrayList<WhiteCard> whiteCards = new ArrayList<>();
    private ArrayList<BlackCard> blackCards = new ArrayList<>();
    private ArrayList<Player> players = new ArrayList<>();
    private int maxPlayers;
    private String status;
    private int id;
    private int hostID;
    private int gameSlot;

    SpaceRepository repository = new SpaceRepository();
    SequentialSpace game = new SequentialSpace();
    SequentialSpace serverLobby;

    // TODO: Add chat?
    // TODO: Add rounds/max round?
    // TODO: Add host?
    // TODO: Add vote count?
    // TODO: Add a check if a player already in the game tries to join again.

    public Game(String gameName,
                ArrayList<WhiteCard> whiteCards,
                ArrayList<BlackCard> blackCards,
                SpaceRepository repository,
                int gameSlot,
                SequentialSpace serverLobby,
                int maxPlayers,
                int id, 
                Player player) {
        this.gameName = gameName;
        this.whiteCards = whiteCards;
        this.blackCards = blackCards;
        this.password = null;
        this.serverLobby = serverLobby;
        this.maxPlayers = maxPlayers;
        this.id = id;
        this.hostID = player.getId();
        this.players.add(player);
        this.gameSlot = gameSlot;

        this.repository = repository;
        this.repository.add("game"+this.gameSlot, game);
    }

    public void run() {

    	try {
    		for (int i = 0; i < 2; i++) {
				game.get(new ActualField("testing"));
				System.out.println("IT'S ALIVE, IT'S ALLLIIIIIVEEEEEEEEE");
			}
			
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
    	
    	/* Game lobby */
    	// ???????Lobby - Type  of Action - String - Integer
		Object[] tuple;
		try {
			tuple = game.get(new ActualField("game"),new FormalField(String.class), new FormalField(Integer.class));
			System.out.println("Game Lobby: Got response: " + tuple[1]);
			if (tuple[1].equals("ready")) {
				// TODO: Ready button: A toggle option to be ready/not be ready.
				readyUpdate((int) tuple[2]);
	        } else if (tuple[1].equals("leave")){
	        	// TODO: Leave game: Return the player to the main lobby, adjust tuple spaces.
	        	playerLeavesGame((int) tuple[2]);
	        }else if (tuple[1].equals("start")){
	        	// TODO: Start game: A button for the host, possibly to entirely replace his ready button.
	        	startGame();
	        }
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
        
    	
		
    	// TODO: Respond to the messages and new players joining in real time.
    	// TODO: Chat?
    }

	private void startGame() {
		// TODO: This is where all the in-game stuff happens.
		// TODO: At first, initialise the game for all players, then add actual game stuff
		
	}
	
	private Player getPlayerwithID(int id) {
	        for (Player player : players) {
	            if (player.getId() == id) {
	                return player;
	            }
	        }
	        return null;
	    }

	private void readyUpdate(int playerID) {
		Player actor = getPlayerwithID(playerID);
        if (actor == null) {
            System.out.print("Game: No player found with ID " + playerID + " in readyUpdate");
            return;
        }
		actor.changeReady();
		
		for (int i = 0; i < players.size(); i++) {
			int recieverID = players.get(i).getId();
			game.put("updateLobby","update", recieverID, actor.getGameSlot());
		}
	}
	
    private void playerLeavesGame(int playerID) {
		Player actor = getPlayerwithID(playerID);
        if (actor == null) {
            System.out.print("Game: No player found with ID " + playerID + " in playerLeavesGame");
            return;
        }
		players.remove(actor);
		
		game.put("updateLobby","leave",actor.getId(), null);
		for (int i = 0; i < players.size(); i++) {
			int recieverID = players.get(i).getId();
			game.put("updateLobby","update", recieverID, new GameSlot(0, "", false));
		}
	}
    
    public void playerJoinsGame(Player actor) {
		for (int i = 0; i < players.size(); i++) {
			int recieverID = players.get(i).getId();
			game.put("updateLobby","update", recieverID, actor.getGameSlot());
		}
	}

	public String getGameName() {
        return this.gameName;
    }

    public String getPassword() {
        return this.password;
    }

    public int getMaxPlayers() {
        return this.maxPlayers;
    }

    public boolean hasPassword() {
        return this.password != null;
    }

    public ArrayList<Player> getPlayers() {
        return this.players;
    }

    public Player FindPlayer(String name) {
    	for (Player player : players) {
            if (Objects.equals(player.getName(), name)) {
                return player;
            }
        }
        return null;
    }

    public String getStatus() {
        return this.status;
    }

    public int getID() {
        return this.id;
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

	public int getGameSlot() {
		return this.gameSlot;
	}

}
