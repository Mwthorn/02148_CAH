package common.src.main;

import java.util.Random;

import org.jspace.ActualField;
import org.jspace.FormalField;
import org.jspace.SequentialSpace;
import org.jspace.SpaceRepository;

public class Server {
    public static void main(String[] argv) {
    	
    	// initialisation();
    	int maxUsers = 20;
    	int numberOfUsers = 0;
    	int[] id = new int[maxUsers];
    	String[] name = new String[maxUsers];
    	
    	SequentialSpace lobby = new SequentialSpace();     // Authentication/Lobby tuple
		//SequentialSpace game = new SequentialSpace();      // Game tuple
		        	
		SpaceRepository repository = new SpaceRepository();
		repository.addGate("tcp://127.0.0.1:9001/?keep");
		repository.add("lobby", lobby);
		//repository.add("game", game);
		
		try {
			Object[] tuple = lobby.get(new ActualField("enter"), new FormalField(String.class),
					new FormalField(Integer.class));
			
			// Creating a random user ID
			Random rand = new Random();
			int  n = rand.nextInt(99999) + 10000;
			id[numberOfUsers] = n;
			name[numberOfUsers] = (String) tuple[1];
			
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
    }
}
