package common.src.main;

import java.util.ArrayList;
import java.util.Random;

import common.src.main.cards.BlackCard;
import common.src.main.cards.WhiteCard;
import org.jspace.ActualField;
import org.jspace.FormalField;
import org.jspace.SequentialSpace;
import org.jspace.SpaceRepository;

public class Server {

    private static ArrayList<WhiteCard> whiteCards;
    private static ArrayList<BlackCard> blackCards;
    private static ArrayList<Game> games;
    private static SequentialSpace lobby;
    private static CardDataBase cardDataBase;
    private static PlayerBase playerBase;

	public static void main(String[] argv) {
    	
    	// initialisation();
    	
    	// Data to setup games
    	int maxGames = 5;
    	boolean[] gamesAvailable = new boolean[maxGames];
		cardDataBase = new CardDataBase();
        playerBase = new PlayerBase();

    	// The place all tuple spaces end up in the server.
    	SpaceRepository repository = new SpaceRepository();
    	
    	// Setup for the lobby tuple space
    	lobby = new SequentialSpace();
		repository.addGate("tcp://127.0.0.1:9001/?keep");
		repository.add("lobby", lobby);

        /* Listening for messages on the tuple space */
		int stop = 0; // Placeholder so the while loop runs forever.

        while(stop == 0) {
			try {
				Object[] tuple = lobby.get(new ActualField("lobby"),new FormalField(String.class), new FormalField(String.class),
						new FormalField(Integer.class));
                System.out.println("Got response: " + tuple[1]);
				if (tuple[1].equals("enter")) {
					System.out.println("Registering user...");
					// Creating a random user ID

					Player p = new Player((String) tuple[2],playerBase.getUniqueId());

					playerBase.addPlayer(p);

					System.out.println("User "+ p.getName() +", the user was assigned th ID: "+ p.getId() +", there are now "+ playerBase.getSize() + " online.");
				    lobby.put("UserID", p.getName(), p.getId());
                }
                else if (tuple[1] == "createGame"){
                    createNewGame(repository, (String)tuple[1]);

				}
				else if (tuple[1].equals("refreshGameList")) {
					String playerID = (String) tuple[3];
					lobby.put("GameListSize", playerID, games.size());
					for (Game game : games) {
						lobby.put("GameList", playerID, game.getGameName(), game.getStatus(), game.hasPassword(), game.getPlayers().size(), game.getMaxPlayers());
					}
				}


			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		
		// Create new game
		// Create a thread to communicate with clients in-game.

        /*
		for (int i = 0; i < maxGames; i++) {
			if (gamesAvailable[i] = true){
				new Thread(new Game(repository, i)).start();
				gamesAvailable[i] = false;
				break;
			}
		}
		*/
		
    }
    
	public static void createNewGame(SpaceRepository repository, String gameName) throws InterruptedException {
        int gameSlot = g.getGameSlot();
        int gameId = g.getGameId();
        int maxPlayers;
        // TODO: Check if GameName is taken.
        if (g.checkName() == false){
        	Game game = new Game(gameName,
                whiteCards,
                blackCards,
                repository,
                gameSlot,
                lobby,
                maxPlayers);
        	new Thread(game).start();
        	lobby.put("gameSetup", true, game);
        } else {
        	lobby.put("gameSetup", false, null);
        }
    }
	
}
