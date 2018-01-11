package common.src.main.server;

import common.src.main.server.database.PlayerBase;
import common.src.main.server.utilities.BlackCard;
import common.src.main.server.utilities.WhiteCard;

import org.jspace.*;

import javax.swing.plaf.synth.SynthTextAreaUI;
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
    private Space local;

    SpaceRepository repository = new SpaceRepository();
    SequentialSpace game = new SequentialSpace();
    SequentialSpace serverLobby;

    // TODO: Add chat?
    // TODO: Add rounds/max round?
    // TODO: Add host?
    // TODO: Add vote count?

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

        // Setup of a local tuple space.
        local = new SequentialSpace();
        GameListener gL = new GameListener(game, local);
        new Thread(gL).start();

        for (Player player : players) {
            game.put("updateLobby", "start", player.getId(), player.getGameSlot());

            ArrayList<WhiteCard> cards = new ArrayList<>();
            for(int i = 0; i < 10; i++) {
                WhiteCard card = drawWhiteCard();
                cards.add(card);
                // STRING - STRING - INT - STRING - INT
                game.put("ingame", "white", player.getId(), card.getSentence(), i);
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
        ArrayList<Player> contestents = new ArrayList<>();

        // Show black card to players
        BlackCard blackCard = drawBlackCard();
        for (Player player : players) {
            // STRING - STRING - INT - STRING - INT
            game.put("ingame", "black", player.getId(), blackCard.getSentence(), blackCard.getBlanks());
            player.resetPickedCard();
            if (player.getId() == chosenID) {
                // TODO: Tell player its not their turn (Chosen) (Do nothing?)
            }
            else {
                contestents.add(player);
                // TODO: Tell players its their turn
                game.put("ingame", "yourturn", player.getId(), null, 0);
            }
        }

        Timeout timeout = new Timeout(local);
        new Thread(timeout).start();


        Boolean state = true;
        try {
            while (state) {
                Object[] tuple = local.get(new ActualField("Game"), new FormalField(String.class));
                if (tuple[1] == "Timeout") {
                    System.out.println("TIMEOUT!! Remaining players will pick a random card");
                    for (Player player : contestents) {
                        if (!player.hasPickedCard()) {
                            Random rand = new Random();
                            int n = rand.nextInt(10) - 1;
                            //pickedCards[contestents.indexOf(player)] = player.getWhiteCards().get(n);
                            player.setPickedCard(n);
                            game.put("ingame", "yourpick", player.getId(), null, n);
                        }
                    }
                    state = false;
                } else if (tuple[1] == "PickedCard") {
                    Object[] tuple2 = local.get(new ActualField("Card"), new FormalField(Integer.class), new FormalField(Integer.class));
                    int clientID = (int) tuple2[1];
                    int cardIndex = (int) tuple2[2];
                    Player player = FindPlayer(clientID);
                    //pickedCards[contestents.indexOf(player)] = player.getWhiteCards().get(cardIndex);
                    player.setPickedCard(cardIndex);
                    game.put("ingame", "yourpick", player.getId(), null, cardIndex);
                    state = false;
                    for (Player player1 : players) {
                        if (!player1.hasPickedCard()) {
                            state = true;
                        }
                    }
                    if (!state) {
                        System.out.println("All players hae picked a card! Skip!");
                        local.put("Timeout", "Cancel");
                    }
                }
            }
            local.get(new ActualField("Game"), new ActualField("TimeoutFinish"));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("All players has picked a card! Now show all players the picked cards!");
        // TODO: Somehow close GameListener in its 'get' state
        // Suggestion: Send tuple with syntax ("gameListener", "exit", ........) and use break; on the loop.

        WhiteCard[] pickedCards = new WhiteCard[contestents.size()-1];

        // TODO: Shuffle the pickedCards (might also want to include a item connected to player)
        Collections.shuffle(contestents);
        for (Player player : contestents) {
            pickedCards[contestents.indexOf(player)] = player.getPickedCard();
        }

        // Show the picked cards to all players
        for (int i = 0; i < contestents.size(); i++) {
            for (Player player : players) {
                game.put("ingame", "picked", player.getId(), pickedCards[i], i);
            }
        }
        timeout = new Timeout(local);
        new Thread(timeout).start();
        state = true;
        Player winnerPlayer = null;
        WhiteCard winnerCard = null;
        Player chosen = FindPlayer(chosenID);
        game.put("ingame", "yourturn", chosen.getId(), null, 0);
        while (state) {
            try {
                Object[] tuple = local.get(new ActualField("Game"), new FormalField(String.class));
                if (tuple[1] == "Timeout") {
                    System.out.println("TIMEOUT!! Pick a random winner card!");
                    Random rand = new Random();
                    int n = rand.nextInt(contestents.size()) - 1;
                    winnerPlayer = contestents.get(n);
                    winnerCard = pickedCards[n];
                }
                else if (tuple[2] == "PickedWinner") {
                    Object[] tuple2 = local.get(new ActualField("Card"), new FormalField(Integer.class), new FormalField(Integer.class));
                    int clientID = (int) tuple2[1];
                    int cardIndex = (int) tuple2[2];
                    if (chosenID == clientID) {
                        winnerPlayer = contestents.get(cardIndex);
                        winnerCard = pickedCards[cardIndex];
                        if (winnerPlayer.getId() == clientID) {
                            System.out.println("WOAH!! Did you just try to choose yourself as the winner?");
                        }
                        state = false;
                    }
                    else {
                        System.out.println("WOAH!! Invalid user tried to pick a winner!");
                    }
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("The winner chosen is: " + winnerPlayer.getName());
        winnerPlayer.addPoints(1);

        // Update results for all players
        for (Player player : players) {
            game.put("ingame", "result", player.getId(), winnerCard.getSentence(), 0);
            game.put("ingame", "result", player.getId(), winnerPlayer.getName(), 0);
            // TODO: Update points for all players
        }
    }


    public void fillWhiteCards() {
        for (Player player : players) {
            int n = player.getWhiteCards().size();
            for (int i = n; i < 10; i++) {
                WhiteCard card = drawWhiteCard();
                player.addWhiteCard(card);
                // STRING - STRING - INT - STRING - INT
                game.put("ingame", "white", player.getId(), card.getSentence(), i);
            }
        }
    }

    public WhiteCard drawWhiteCard() {
        WhiteCard card = whiteCards.get(0);
        whiteCards.remove(0);
        return card;
    }

    public BlackCard drawBlackCard() {
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
        int actorSlot = actor.getGameSlot().getSlot();
		players.remove(actor);
		
		game.put("updateLobby", "leave", actor.getId(), null);
        for (Player player : players) {
            game.put("updateLobby", "update", player.getId(), new GameSlot(actorSlot, "", false));
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

    public Player FindPlayer(int ID) {
        for (Player player : players) {
            if (player.getId() == ID) {
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
    	int actorID = actor.getId();
    	// Checks if the player is already in the game
    	for (Player player : players) {
			if (player.getId() == actorID) {
				System.out.println(actor.getName()+" is already in the game.");
				game.put("updateLobby", "error", null, null);
				return;
			}
		}
    	
        // Sends all players current game slot to the joining player.
        for (Player player : players){
        	game.put("updateLobby", "update", actor.getId(), player.getGameSlot());
        }
        
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
            game.put("updateLobby", "update", player.getId(), actor.getGameSlot());
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



/*
 * Client Commands:
 * put("ingame", "white", userID)
 * put("ingame", "white", "white", userID)
 * put("ingame", "white", "white", "white", UserID)
 * get("ingame", "black", numberOfBlanks)
 *
 *
 * Server Commands:
 * put("ingame", "black", numberOfBlanks, recieverID)
 * get("ingame", "white", userID) X numberOfClients
 * get("ingame", "white", "white", userID)
 * get("ingame", "white", "white", "white", UserID)
 *
 */


















