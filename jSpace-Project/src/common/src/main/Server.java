package common.src.main;

import org.jspace.ActualField;
import org.jspace.FormalField;
import org.jspace.SequentialSpace;
import org.jspace.SpaceRepository;

public class Server {
    public static void main(String[] argv) {
    	
    	// initialisation();
    	
    	SequentialSpace lobby = new SequentialSpace();     // Authentication/Lobby tuple
		//SequentialSpace game = new SequentialSpace();      // Game tuple
		        	
		SpaceRepository repository = new SpaceRepository();
		repository.addGate("tcp://127.0.0.1:9001/?keep");
		repository.add("lobby", lobby);
		//repository.add("game", game);
		
		try {
			Object[] tuple = lobby.get(new FormalField(String.class));
			
			
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
    }
}
