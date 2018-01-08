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
    private static ArrayList<Game> Games;
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

					System.out.println("User "+ p.getName() +", the user was assigned th ID: "+ p.getId() +", there are now "+ playerBase.getSize() + "online.");
				    lobby.put("UserID", p.getName(), p.getId());
                }
                else if (tuple[1] == "createGame"){

                    createNewGame(repository);

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
    
	public static void createNewGame(SpaceRepository repository) throws InterruptedException {
        Object[] tupleGameInfo = lobby.get(new ActualField("GameInfo"), new FormalField(String.class),
                new FormalField(Integer.class));
        int id = (int) tupleGameInfo[1];
        // TODO: Check if GameName is taken

        String gameName = (String) tupleGameInfo[0];
        int gameSlot = 0;

        Game game = new Game(gameName,
                whiteCards,
                blackCards,
                repository,
                gameSlot,
                lobby);
        new Thread(game).start();


    }
	
}
