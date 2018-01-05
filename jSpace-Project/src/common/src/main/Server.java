package common.src.main;

import java.util.Random;

import org.jspace.ActualField;
import org.jspace.FormalField;
import org.jspace.SequentialSpace;
import org.jspace.SpaceRepository;

public class Server {
    public static void main(String[] argv) {
    	
    	// initialisation();
    	
    	// Data to setup games
    	int maxGames = 5;
    	boolean[] gamesAvailable = new boolean[maxGames];
    	
    	// Some user data, maybe make it into a class?
    	int maxUsers = 20;
    	int numberOfUsers = 0;
    	int[] id = new int[maxUsers];
    	String[] name = new String[maxUsers];
    	
    	// The place all tuple spaces end up in the server.
    	SpaceRepository repository = new SpaceRepository();
    	
    	// Setup for the lobby tuple space
    	SequentialSpace lobby = new SequentialSpace();
		repository.addGate("tcp://127.0.0.1:9001/?keep");
		repository.add("lobby", lobby);
		
		
		/* Listening for messages on the tuple space */
		int stop = 0; // Placeholder so the while loop runs forever.
		while(stop != 0){
			try {
				Object[] tuple = lobby.get(new FormalField(String.class), new FormalField(String.class),
						new FormalField(Integer.class));
				if (tuple[0] == "enter"){
					
					// Creating a random user ID
					Random rand = new Random();
					int  n = rand.nextInt(99999) + 10000;
					id[numberOfUsers] = n;
					name[numberOfUsers] = (String) tuple[1];
					System.out.println("User "+name[numberOfUsers]+", the user was assigned th ID: "+id[numberOfUsers]+", there is now "+numberOfUsers+"online.");
				} else if (true){
					
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		
		// Create new game
		// Create a thread to communicate with clients in-game.
		
		for (int i = 0; i < maxGames; i++) {
			if (gamesAvailable[i] = true){
				new Thread(new Game(repository, i)).start();
				gamesAvailable[i] = false;
				break;
			}
		}
		
    }
    
	public static void createNewGame(SpaceRepository repository) {
		
		
	}
	
}
