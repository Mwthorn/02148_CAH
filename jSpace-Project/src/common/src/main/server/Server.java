package common.src.main.server;

import java.util.ArrayList;

import common.src.main.client.GamePreview;
import common.src.main.server.database.CardDataBase;
import common.src.main.server.database.GameBase;
import common.src.main.server.database.PlayerBase;
import common.src.main.server.utilities.BlackCard;
import common.src.main.server.utilities.WhiteCard;
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
    private static GameBase gameBase;
    private static SpaceRepository repository = new SpaceRepository();

	public static void main(String[] argv) {
    	
    	// initialisation();
    	
    	// Data to setup games
		cardDataBase = new CardDataBase();
        playerBase = new PlayerBase();
        gameBase = new GameBase();
    	
    	
    	// Setup for the lobby tuple space
    	lobby = new SequentialSpace();
		repository.addGate("tcp://127.0.0.1:9001/?keep");
		repository.add("lobby", lobby);

        /* Listening for messages on the tuple space */
        while(true) {
			try {
				// Lobby - Type of Action - String - Integer
				Object[] tuple = lobby.get(new ActualField("lobby"),new FormalField(String.class), new FormalField(String.class), new FormalField(Integer.class));
                System.out.println("Got response: " + tuple[1]);
				if (tuple[1].equals("enter")) {
					System.out.println("Registering user...");
					
					// Creating a random user ID
					Player p = new Player((String) tuple[2],playerBase.getUniqueId());

					playerBase.addPlayer(p);

					System.out.println("User "+ p.getName() +", the user was assigned th ID: "+ p.getId() +", there are now "+ playerBase.getSize() + " online.");
				    lobby.put("UserID", p.getName(), p.getId());
                }
                else if (tuple[1].equals("createGame")){
                	System.out.println("Creating new game...");
                    createNewGame((String) tuple[2], (int) tuple[3]);
				}
				else if (tuple[1].equals("refreshGameList")) {
					int playerID = (int) tuple[3];
					lobby.put("GameListSize", playerID, games.size());
					for (Game game : gameBase.getGames()) {
						lobby.put("GameList", playerID, new GamePreview(game));
					}
				} else if (tuple[1].equals("joinGame")) {
					
				} else if (tuple[1].equals("signOut")) {

				}


			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
    }
    
	public static void createNewGame(String gameName, int hostID) throws InterruptedException {
        int gameSlot = gameBase.getGameSlot();
        int gameId = gameBase.getGameId();
        int maxPlayers = 0;
        Player player = playerBase.getPlayerwithID(hostID);
        
        Game game = new Game(gameName,
            whiteCards,
            blackCards,
            repository,
            gameSlot,
            lobby,
            maxPlayers, 
            gameId, 
            player);
    	new Thread(game).start();
    	gameBase.addGame(game);
    	
    	lobby.put("gameCreated",gameId, hostID, gameSlot);
    	System.out.println("New game created by: "+player.getName()+". The name of the game is '"+game.getGameName()
    	+"', and there is currrently "+game.getMaxPlayers()+" in the game.");
    }
	
}
