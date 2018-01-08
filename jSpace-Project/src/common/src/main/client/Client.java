package common.src.main.client;

import java.io.IOException;
import java.net.UnknownHostException;

import common.src.main.server.Game;
import org.jspace.ActualField;
import org.jspace.FormalField;
import org.jspace.RemoteSpace;

public class Client {
	private static RemoteSpace lobby;
	private static int userID;
	private static int gameID;

    public static void main(String[] args) {
    	/* Login */
    	// Create login GUI and request name of user and IP to server.
    	
    	System.out.println("hej");
    	/* Connect to server using GUI info */
		
			try {
				loginUser("Alex", "127.0.0.1");
				
				System.out.println("Trying to create game");
				createNewGame();

			} catch (UnknownHostException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			

		
		
		/* Lobby */
		// Create lobby GUI
		
		// Tons of different lobby features

		// Do lobby action
		
		
		
		/*Enter game state
		 * cardsAgainstHumanity();
		 */
    }

	private static void createNewGame() {
		lobby.put("lobby", "createGame", "no nobs plx", userID);
		
		try {
			System.out.println("Trying to recieve info");
			Object[] info = lobby.get(new ActualField("gameCreated"),new FormalField(Integer.class), new ActualField(userID));
			gameID = (int) info[2];
			System.out.println("Game succesfully created");
			
			// Stuff to join game???
			
			
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public static void loginUser(String name, String IP) throws IOException, InterruptedException {
		lobby = new RemoteSpace("tcp://" + IP + ":9001/lobby?keep");

		//lobby.put("test");
		lobby.put("lobby","enter",name,0);

		Object[] tuple = lobby.get(new ActualField("UserID"),new ActualField(name), new FormalField(Integer.class));
		userID = (int) tuple[2];
		System.out.println("Client was assigned ID: " + userID);
	}

	private static void cardsAgainstHumanity() {
		// Create game GUI
		// Tons of different game features, move it to another function?
		
	}

	public static GamePreview[] getGameList() throws InterruptedException {
		lobby.put("lobby", "refreshGameList", "", userID);

		Object[] tuple = lobby.get(new ActualField("GameListSize"), new ActualField(userID), new FormalField(Integer.class));
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
					new FormalField(Integer.class),
					new FormalField(Integer.class));
			games[i] = new GamePreview((String) gT[1], (String) gT[2], (Boolean) gT[3], (int) gT[4], (int) gT[5], (int) gT[6]);
		}
		return games;
	}

	public static void joinGame(int ID) throws InterruptedException {
		
	}
}
