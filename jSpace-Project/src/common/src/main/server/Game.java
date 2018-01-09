package common.src.main.server;

import common.src.main.server.database.PlayerBase;
import common.src.main.server.utilities.BlackCard;
import common.src.main.server.utilities.WhiteCard;
import org.jspace.ActualField;
import org.jspace.FormalField;
import org.jspace.SequentialSpace;
import org.jspace.SpaceRepository;

import java.util.ArrayList;

public class Game implements Runnable {

    private String gameName;
    private String password;
    private ArrayList<WhiteCard> whiteCards = new ArrayList<>();
    private ArrayList<BlackCard> blackCards = new ArrayList<>();
    private PlayerBase players = new PlayerBase();
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
        this.players.addPlayer(player);
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

	private void readyUpdate(int playerID) {
    	Player actor = players.getPlayerwithID(playerID);
    	int actorNumber = players.getPlayerNumber(actor);
    	actor.changeReady();
    	for (int i = 0; i < players.getSize(); i++) {
    		int recieverID = players.getPlayerID(i);
			game.put("updateLobby", "ready", recieverID, i, actorNumber, null);
		}
	}
	
    private void playerLeavesGame(int playerID) {
    	Player actor = players.getPlayerwithID(playerID);
    	players.removePlayer(actor);
    	// TODO: send message to force the acting player to disconnect.
    	for (int i = 0; i < players.getSize(); i++) {
    		int recieverID = players.getPlayerID(i);
    		String playerName = players.getPlayerName(i);
			game.put("updateLobby", "update", recieverID, i, null, playerName);
		}
	}
    
    private void playerJoinsGame(int playerID) {
    	Player actor = players.getPlayerwithID(playerID);
    	players.addPlayer(actor);
    	// TODO: send message to initialise the acting player to the game.
    	for (int i = 0; i < players.getSize(); i++) {
    		int recieverID = players.getPlayerID(i);
    		String playerName = players.getPlayerName(i);
			game.put("updateLobby", "update", recieverID, i, null, playerName);
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
        return this.players.getPlayers();
    }

    public Player FindPlayer(String name) {
        return players.getName(name);
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
        players.addPlayer(player);
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
