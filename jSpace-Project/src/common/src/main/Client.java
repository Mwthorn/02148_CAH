package common.src.main;

import java.io.IOException;
import java.io.InterruptedIOException;
import java.net.UnknownHostException;
import java.rmi.Remote;

import org.jspace.ActualField;
import org.jspace.FormalField;
import org.jspace.RemoteSpace;

public class Client {

	private static RemoteSpace lobby;
	private static int userID;

    public static void main(String[] args) {

    	/* Login */
    	// Create login GUI and request name of user and IP to server.
    	
    	
    	/* Connect to server using GUI info */
		try {
			lobby = new RemoteSpace("tcp://127.0.0.1:9001/lobby?keep");
			//lobby.put("test");
			String name = "Alex";
			lobby.put("lobby","enter",name,0);
			Object[] tuple = lobby.get(new ActualField("UserID"),new ActualField(name), new FormalField(Integer.class));
			System.out.println("Client was assigned ID: " + tuple[2]);

		} catch (IOException | InterruptedException e) {
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

	private static void cardsAgainstHumanity() {
		// Create game GUI
		// Tons of different game features, move it to another function
		
	}

	public static GamePreview[] refreshGameList(RemoteSpace lobby) throws InterruptedException {
		lobby.put("lobby", "refreshGameList", "", userID);

		Object[] tuple = lobby.get(new ActualField("GameListSize"),new ActualField(userID), new FormalField(Integer.class));
		Object[] gT;
		int n = (int) tuple[1];
		GamePreview[] games = new GamePreview[n];
		System.out.println("Got " + n + " games from server!");
		for (int i = 0; i < n; i++) {
			gT = lobby.get(new ActualField("GameListSize"),
					new ActualField(userID),
					new FormalField(String.class),
					new FormalField(String.class),
					new FormalField(Boolean.class),
					new FormalField(Integer.class),
					new FormalField(Integer.class));
			games[i] = new GamePreview((String) gT[1], (String) gT[2], (Boolean) gT[3], (int) gT[4], (int) gT[5]);
		}
		return games;
	}
}
