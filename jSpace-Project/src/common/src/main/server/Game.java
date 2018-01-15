package common.src.main.server;

import common.src.main.server.utilities.BlackCard;
import common.src.main.server.utilities.WhiteCard;

import org.jspace.*;

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
    private final int whiteCardsPerPlayer = 10;

    SpaceRepository repository = new SpaceRepository();
    SequentialSpace listener = new SequentialSpace();
    SequentialSpace talker = new SequentialSpace();
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
        System.out.println("The size of the white deck: " + whiteCards.size() + " " + this.whiteCards.size());
        this.blackCards = blackCards;
        System.out.println("The size of the black deck: " + blackCards.size() + " " + this.blackCards.size());
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
        for (int i = 0; i < maxPlayers; i++) {
            slotOccupied[i] = false;
        }
        slotOccupied[0] = true;
        player.setGameSlot(0);
        this.status = "Waiting for players...";

        this.repository = repository;
        System.out.println("Added game to repository with gameslot: " + this.gameSlot);
        this.repository.add("talker"+this.gameSlot, listener);
        this.repository.add("listener"+this.gameSlot, talker);
        
        talker.put("updateLobby", "update", hostID, player.getGameSlot());
    }

    public void run() {
		System.out.println("IT'S ALIVE, IT'S ALLLIIIIIVEEEEEEEEE");
		
    	/* Game lobby */
    	while (true) {
            // ???????Lobby - Type  of Action - String - Integer
            System.out.println("Now listening in game lobby...");
            Object[] tuple;
            try {
                tuple = listener.get(new ActualField("game"), new FormalField(String.class), new FormalField(Integer.class));
                System.out.println("Game Lobby: Got response: " + tuple[1]);
                if (tuple[1].equals("ready")) {
                    readyUpdate((int) tuple[2]);
                    if (players.size() > 2) {
                        Boolean allReady = true;
                        for (Player player : players) {
                            if (!player.getReady()) {
                                allReady = false;
                            }
                        }
                        if (allReady) {
                        	status = "Game Started";
                            startGame();
                        }
                    }
                } else if (tuple[1].equals("leave")) {
                    playerLeavesGame((int) tuple[2]);
                } else if (tuple[1].equals("start")) {
                    if ((int) tuple[2] == hostID) {
                        startGame();
                    }
                    else {
                        System.out.println("WOAH!! A non-host player tried to start game!");
                    }
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
        GameListener gL = new GameListener(listener, local);
        new Thread(gL).start();
        // Suggestion: Send tuple with syntax ("gameListener", "exit", ........) and use break; on the loop.


        for (Player player : players) {
            talker.put("updateLobby", "start", player.getId(), player.getGameSlot());
            talker.put("starting", player.getId(), players.size());
            ArrayList<WhiteCard> cards = new ArrayList<>();
            for(int i = 0; i < this.whiteCardsPerPlayer; i++) {
                WhiteCard card = drawWhiteCard();
                cards.add(card);
                // STRING - STRING - INT - STRING - INT
                player.setWhiteCard(card,i);
                talker.put("ingame", "white", player.getId(), card.getSentence(), i);
            }
        }


        Random rand = new Random();
        this.currentTurn = rand.nextInt(players.size()) - 1;
        Boolean state = true;
        while (state) {
            nextRound();
            if (blackCards.size() <= 0) {
                System.out.println("There are no further black cards to continue!");
                state = false;
            }
            else if (whiteCards.size() <= players.size()*2) {
                System.out.println("There are not enough white cards to continue!");
                state = false;
            }
            // TODO: Max rounds? Points to win?
        }
	}

    public void nextRound() {
        this.currentTurn++;
        if (this.currentTurn == players.size()) {
            this.currentTurn = 0;
        }

        // Makes sure that all players have this.whiteCardsPerPlayer white cards each
        fillWhiteCards();

        // Pick the chosen player
        int chosenID = players.get(this.currentTurn).getId();
        ArrayList<Player> contestents = new ArrayList<>();

        // Show black card to players
        BlackCard blackCard = drawBlackCard();
        for (Player player : players) {
            // STRING - STRING - INT - STRING - INT
            talker.put("ingame", "black", player.getId(), blackCard.getSentence(), blackCard.getBlanks());
            player.resetPickedCards();
            if (player.getId() == chosenID) {
                // TODO: Tell player its not their turn (Chosen) (Do nothing?)
            }
            else {
                contestents.add(player);
                talker.put("ingame", "yourturn", player.getId(), null, 0);
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
                        for (int i = 0; i < blackCard.getBlanks(); i++) {
                            if (!player.hasPickedCard(i)) {
                                Random rand = new Random();
                                int n = rand.nextInt(this.whiteCardsPerPlayer - 1);
                                //pickedCards[contestents.indexOf(player)] = player.getWhiteCards().get(n);
                                player.chooseWhiteCard(n);
                                System.out.println("Set picked card for PLAYER " + player.getId() + " to card " + n);
                                talker.put("ingame", "yourpick", player.getId(), null, n);
                                System.out.println("Done!");
                            }
                        }
                    }
                    state = false;
                } else if (tuple[1] == "PickedCard") {
                    Object[] tuple2 = local.get(new ActualField("Card"), new FormalField(Integer.class), new FormalField(Integer.class));
                    int clientID = (int) tuple2[1];
                    int cardIndex = (int) tuple2[2];
                    Player player = FindPlayer(clientID);
                    //pickedCards[contestents.indexOf(player)] = player.getWhiteCards().get(cardIndex);
                    player.chooseWhiteCard(cardIndex);
                    talker.put("ingame", "yourpick", player.getId(), null, cardIndex);
                    state = false;
                    for (Player player1 : players) {
                        for (int i = 0; i < blackCard.getBlanks(); i++) {
                            if (!player1.hasPickedCard(i)) {
                                state = true;
                            }
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
        System.out.println("All players has picked their cards! Now show all players the picked cards!");

        WhiteCard[][] pickedCards = new WhiteCard[contestents.size()][blackCard.getBlanks()];

        Collections.shuffle(contestents);
        for (Player player : contestents) {
            for (int i = 0; i < blackCard.getBlanks(); i++) {
                pickedCards[contestents.indexOf(player)][i] = player.getPickedCards().get(i);
            }
        }

        // Show the picked cards to all players
        for (int i = 0; i < contestents.size(); i++) {
            for (Player player : players) {
                talker.put("ingame", "picked", player.getId(), pickedCards[i][0].getSentence(), i);
                //
                //talker.put("ingame", "picked", player.getId(), pickedCards[i], i);
            }
        }
        timeout = new Timeout(local);
        new Thread(timeout).start();
        state = true;
        Player winnerPlayer = null;
        WhiteCard winnerCards[] = new WhiteCard[blackCard.getBlanks()];
        Player chosen = FindPlayer(chosenID);
        talker.put("ingame", "yourturn", chosen.getId(), null, 0);
        while (state) {
            try {
                Object[] tuple = local.get(new ActualField("Game"), new FormalField(String.class));
                if (tuple[1] == "Timeout") {
                    System.out.println("TIMEOUT!! Pick a random winner!");
                    Random rand = new Random();
                    int n = rand.nextInt(contestents.size()-1);
                    winnerPlayer = contestents.get(n);
                    winnerCards = pickedCards[n];
                    state = false;
                }
                else if (tuple[1] == "PickedWinner") {
                    Object[] tuple2 = local.get(new ActualField("Card"), new FormalField(Integer.class), new FormalField(Integer.class));
                    int clientID = (int) tuple2[1];
                    int cardIndex = (int) tuple2[2];
                    if (chosenID == clientID) {
                        winnerPlayer = contestents.get(cardIndex);
                        winnerCards = pickedCards[cardIndex];
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
        System.out.println(blackCard.getSentence());
        for (int i = 0; i < blackCard.getBlanks(); i++) {
            System.out.println(winnerCards[i].getSentence());
        }
        winnerPlayer.addPoints(1);

        // Update results for all players
        for (Player player : players) {
            for (int i = 0; i < blackCard.getBlanks(); i++) {
                talker.put("ingame", "resultCards", player.getId(), winnerCards[i].getSentence(), 0);
            }
            talker.put("ingame", "resultPlayer", player.getId(), winnerPlayer.getName(), 0);
            // TODO: Update points for all players
            talker.put("ingame", "points", player.getId(), player.getGameSlot().getSlot(), winnerPlayer.getPoints());
        }
        try {
            System.out.println("Sleeping...");
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    public void fillWhiteCards() {
        for (Player player : players) {
            WhiteCard[] whiteCards1 = player.getWhiteCards();
            for (int i = 0; i < this.whiteCardsPerPlayer; i++) {
                if (whiteCards1[i] == null) {
                    WhiteCard card = drawWhiteCard();
                    player.setWhiteCard(card, i);
                    // STRING - STRING - INT - STRING - INT
                    talker.put("ingame", "white", player.getId(), card.getSentence(), i);
                }
            }
        }
    }

    public WhiteCard drawWhiteCard() {
        System.out.println("Size of the white deck: " + whiteCards.size());
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
            talker.put("updateLobby", "update", recieverID, actor.getGameSlot());
        }
	}
	
    private void playerLeavesGame(int playerID) {
		Player actor = getPlayerwithID(playerID);
        if (actor == null) {
            System.out.print("Game: No player found with ID " + playerID + " in playerLeavesGame");
            return;
        }
        int actorSlot = actor.getGameSlot().getSlot();
        if (players.size() == maxPlayers){
        	status = "Waiting for players...";
        }
		players.remove(actor);
		
		talker.put("updateLobby", "leave", actor.getId(), null);
        for (Player player : players) {
            talker.put("updateLobby", "update", player.getId(), new GameSlot(actorSlot, "", false));
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
    	
    	// Check if the game has started.
    	if (status == "Game Full"){
    		System.out.println("Player with ID "+actorID+" attempted to join a full game.");
    		return;
    	}
    	
    	// Checks if the player is already in the game
    	for (Player player : players) {
			if (player.getId() == actorID) {
				System.out.println(actor.getName()+" is already in the game.");
				talker.put("updateLobby", "error", null, null);
				return;
			}
		}
    	
        // Sends all players current game slot to the joining player.
        for (Player player : players){
        	talker.put("updateLobby", "update", actor.getId(), player.getGameSlot());
        }
        
    	// Adds the player to the game.
        players.add(actor);
        
        // Check if the game is full.
        if (players.size() == maxPlayers){
        	status = "Game Full";
        }
        
        // Set the players game slot.
        for (int i = 0; i < maxPlayers; i++) {
			if (!slotOccupied[i]){
				actor.setGameSlot(i);
				slotOccupied[i] = true;
				break;
			}
		}
        
        // Sends an update to all other players currently in the game
        for (Player player : players) {
            talker.put("updateLobby", "update", player.getId(), actor.getGameSlot());
        }
        System.out.println("Added player " + actor.getId() + " to GameID: " + this.id);
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


















