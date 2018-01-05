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

	private static ArrayList<Player> players;
    private static ArrayList<WhiteCard> whiteCards;
    private static ArrayList<BlackCard> blackCards;
    private static ArrayList<Game> Games;
    private static SequentialSpace lobby;

	public static void main(String[] argv) {
    	
    	// initialisation();
    	
    	// Data to setup games
    	int maxGames = 5;
    	boolean[] gamesAvailable = new boolean[maxGames];

    	// The place all tuple spaces end up in the server.
    	SpaceRepository repository = new SpaceRepository();
    	
    	// Setup for the lobby tuple space
    	lobby = new SequentialSpace();
		repository.addGate("tcp://127.0.0.1:9001/?keep");
		repository.add("lobby", lobby);
		
		
		/* Listening for messages on the tuple space */
		int stop = 0; // Placeholder so the while loop runs forever.
		while(stop != 0){
			try {
				Object[] tuple = lobby.get(new ActualField("lobby"),new FormalField(String.class), new FormalField(String.class),
						new FormalField(Integer.class));
				if (tuple[1] == "enter"){
					
					// Creating a random user ID

                    // TODO: ID must be unique (check if already exists)
					Random rand = new Random();
					int n = rand.nextInt(99999) + 10000;
					Player p = new Player((String) tuple[1],n);

					players.add(p);

					System.out.println("User "+ p.getName() +", the user was assigned th ID: "+ p.getId() +", there is now "+ players.size() +"online.");
				    lobby.put("UserID", p.getName(), p.getId());
                } else if (tuple[1] == "createGame"){
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
