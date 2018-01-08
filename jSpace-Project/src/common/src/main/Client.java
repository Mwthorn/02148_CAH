package common.src.main;

import java.io.IOException;
import java.net.UnknownHostException;

import org.jspace.ActualField;
import org.jspace.FormalField;
import org.jspace.RemoteSpace;
import org.jspace.SequentialSpace;
import org.jspace.SpaceRepository;

public class Client {
	private static RemoteSpace lobby;
	
    public static void main(String[] args) {
    	/* Login */
    	// Create login GUI and request name of user and IP to server.
    	
    	
    	/* Connect to server using GUI info */
		try {
			lobby = new RemoteSpace("tcp://127.0.0.1:9001/lobby?keep");
			//lobby.put("test");
			lobby.put("lobby","enter","Alex",0);
			
			
			createNewGame();
			
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		/* Lobby */
		// Create lobby GUI
		
		// Tons of different lobby features

		// Do lobby action
		
		
		
		/*Enter game state
		cardsAgainstHumanity();

        RemoteSpace lobby;
        
			try {
				lobby = new RemoteSpace("tcp://127.0.0.1:9001/chat?keep");
				lobby.put("enter",0);

				
			} catch (UnknownHostException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		*/
    }

	private static void createNewGame() {
		lobby.put("createGame","no nobs plx", 0);
		
		try {
			Object[] info = lobby.get(new ActualField("gameSetup"),new FormalField(boolean.class), new FormalField(Game.class));
			
			if ((boolean) (info[1] = true)){
				//Create game
			} else {
				// game name already taken, print error message to user and try with another name.
			}
			
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	private static void cardsAgainstHumanity() {
		// Create game GUI
		// Tons of different game features, move it to another function?
		
	}
}
