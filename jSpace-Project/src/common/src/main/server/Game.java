package common.src.main.server;

import common.src.main.server.database.PlayerBase;
import common.src.main.server.utilities.BlackCard;
import common.src.main.server.utilities.WhiteCard;

import org.jspace.ActualField;
import org.jspace.FormalField;
import org.jspace.SequentialSpace;
import org.jspace.SpaceRepository;

import java.util.*;

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
    private int currentTurn;
    private int gameSlot;
    private boolean[] slotOccupied;

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
        Collections.shuffle(this.whiteCards);
        Collections.shuffle(this.blackCards);
        this.password = null;
        this.serverLobby = serverLobby;
        this.maxPlayers = maxPlayers;
        this.id = id;
        this.hostID = player.getId();
        this.players.add(player);
        this.gameSlot = gameSlot;
        this.slotOccupied = new boolean[maxPlayers];

        this.repository = repository;
        this.repository.add("game"+this.gameSlot, game);
    }

    public void run() {

    	try {
			game.get(new ActualField("testing"));
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		System.out.println("IT'S ALIVE, IT'S ALLLIIIIIVEEEEEEEEE");
		
    	/* Game lobby */
    	while (true) {
            // ???????Lobby - Type  of Action - String - Integer
            Object[] tuple;
            try {
                tuple = game.get(new ActualField("game"), new FormalField(String.class), new FormalField(Integer.class));
                System.out.println("Game Lobby: Got response: " + tuple[1]);
                if (tuple[1].equals("ready")) {
                    readyUpdate((int) tuple[2]);
                    Boolean allReady = true;
                    for (Player player : players) {
                        if (!player.getReady()) {
                            allReady = false;
                        }
                    }
                    if (allReady) {
                        startGame();
                    }
                } else if (tuple[1].equals("leave")) {
                    playerLeavesGame((int) tuple[2]);
                } else if (tuple[1].equals("start")) {
                    startGame();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        
    	
		
    	// TODO: Respond to the messages and new players joining in real time.
    	// TODO: Chat?
    }

	private void startGame() {
		// This is where all the in-game stuff happens.
		// TODO: At first, initialise the game for all players, then add actual game stuff

        for (Player player : players) {
            game.put("updateLobby", "start", player.getId(), player.getGameSlot());

            ArrayList<WhiteCard> cards = new ArrayList<>();
            for(int i = 0; i < 10; i++) {
                WhiteCard card = takeWhiteCard();
                cards.add(card);
                // STRING - INT - STRING - INT
                game.put("white", player.getId(), card.getSentence(), i);
            }
            player.setWhiteCards(cards);
        }

        Random rand = new Random();
        int n = rand.nextInt(players.size()) - 1;
        this.currentTurn = n;
        nextRound();
	}

    public void nextRound() {
        this.currentTurn++;
        if (this.currentTurn == players.size()) {
            this.currentTurn = 0;
        }

        // Makes sure that all players have 10 white cards each
        fillWhiteCards();

        // Pick the chosen player
        int chosenID = players.get(this.currentTurn).getId();

        // Show black card to players
        BlackCard blackCard = takeBlackCard();
        for (Player player : players) {
            // STRING - INT - STRING - INT
            game.put("black", player.getId(), blackCard.getSentence(), blackCard.getBlanks());
            player.resetPickedCard();
            // TODO: Tell players its their turn
        }

        // TODO: Listen on players to pick their white card
        boolean state = true;
        int timeout = 0;
        while (state) {
            try {
                Thread.sleep(1000);
                timeout++;
                if (timeout < 30) {
                    // TODO: Choose a random for choose that didn't pick a card
                    state = false;
                }
                else {
                    state = false;
                    for (Player player : players) {
                        if (!player.hasPickedCard()) {
                            if (player.getId() != chosenID) {
                                state = true;
                            }
                        }
                    }
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        // Get white cards

        ArrayList<WhiteCard> pickedCards = new ArrayList<>();
        for (Player player : players) {
            pickedCards.add(player.getPickedCard());
        }

        // TODO: Listen on chosen player to choose the winner card
        state = true;
        timeout = 0;
        while (state) {
            try {
                Thread.sleep(1000);
                timeout++;
                if (timeout < 30) {
                    // TODO: Choose a random winner if didn't pick a card
                    state = false;
                }
                else {
                    if ()
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

    public void fillWhiteCards() {
        for (Player player : players) {
            int n = player.getWhiteCards().size();
            for (int i = n; i < 10; i++) {
                WhiteCard card = takeWhiteCard();
                player.addWhiteCard(card);
                // STRING - INT - STRING - INT
                game.put("white", player.getId(), card.getSentence(), i);
            }
        }
    }

    public WhiteCard takeWhiteCard() {
        WhiteCard card = whiteCards.get(0);
        whiteCards.remove(0);
        return card;
    }

    public BlackCard takeBlackCard() {
        BlackCard card = blackCards.get(0);
        blackCards.remove(0);
        return card;
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

        for (Player player : players) {
            int recieverID = player.getId();
            game.put("updateLobby", "update", recieverID, actor.getGameSlot());
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
        for (Player player : players) {
            int recieverID = player.getId();
            game.put("updateLobby", "update", recieverID, new GameSlot(0, "", false));
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

    public void addPlayerToGame(Player actor) {
    	// Adds the player to the game.
        players.add(actor);
        
        // Set the players game slot.
        for (int i = 0; i < maxPlayers; i++) {
			if (slotOccupied[i] == false){
				actor.setGameSlot(i);
				slotOccupied[i] = true;
				break;
			}
		}
        
        // Sends an update to all other players currently in the game
        for (Player player : players) {
            int recieverID = player.getId();
            game.put("updateLobby", "update", recieverID, actor.getGameSlot());
        }
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
