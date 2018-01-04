package common.src.main;

import java.io.IOException;
import java.net.UnknownHostException;

import org.jspace.ActualField;
import org.jspace.FormalField;
import org.jspace.RemoteSpace;
import org.jspace.SequentialSpace;
import org.jspace.SpaceRepository;

public class Client {
    public static void main(String[] args) {
    	RemoteSpace space;
    	
    	/* Login */
    	// Create login GUI and request name of user and IP to server.
    	
    	/* Connect to server using GUI info */
		try {
			space = new RemoteSpace("tcp://127.0.0.1:9001/lobby?keep");
			
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		/* Lobby */
		// Create lobby GUI
		
		// Tons of different lobby features

		// Do lobby action
		
		
		
		
		/* Enter game state */
		cardsAgainstHumanity();
    }

	private static void cardsAgainstHumanity() {
		// Create game GUI
		// Tons of different game features, move it to another function
		
	}
}
