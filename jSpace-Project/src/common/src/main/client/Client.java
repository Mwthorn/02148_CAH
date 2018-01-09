package common.src.main.client;

import java.io.IOException;
import java.net.UnknownHostException;
import java.util.ArrayList;

import common.src.main.philosopher1;
import common.src.main.server.Game;
import common.src.main.server.Player;
import org.jspace.ActualField;
import org.jspace.FormalField;
import org.jspace.RemoteSpace;
import org.jspace.SequentialSpace;
import org.jspace.Space;

public class Client {
	private static RemoteSpace lobby, game;
	private static int userID;
	private static int gameID;
	private static String serverIP;
	private static String name;

    public static void main(String[] args) {
    	/* Login */
    	// Create login GUI and request name of user and IP to server.
    	
    	System.out.println("hej");
    	/* Connect to server using GUI info */
		
			try {
				loginUser();
				
				
				System.out.println("Trying to create game");
				createNewGame();

				joinGame();
				
				// 3 threads
				gameLobby();
				

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
			Object[] info = lobby.get(new ActualField("gameCreated"), new ActualField(userID), new FormalField(Integer.class));
			int gameSlot = (int) info[2];
			
			// Connects the host to the tuple space.
			game = new RemoteSpace("tcp://" + serverIP + ":9001/game" + gameSlot + "?keep");
			
			game.put("testing");
			
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void loginUser() throws IOException, InterruptedException {
		// TODO: The two lines below assigning IP and name should be retrieved
		// when first signing in to the lobby.
		serverIP = "127.0.0.1";
		name = "Alex";
		
		lobby = new RemoteSpace("tcp://" + serverIP + ":9001/lobby?keep");

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
			gT = lobby.get(new ActualField("GameList"),
					new ActualField(userID),
					new FormalField(Game.class));
			games[i] = (GamePreview) gT[2];
		}
		return games;
	}

	public static void joinGame() throws InterruptedException {
		String stringID = Integer.toString(userID);
		lobby.put("lobby", "joinGame", stringID, gameID);

		Object[] info = lobby.get(new ActualField("joinedGame"), new ActualField(userID), new FormalField(Integer.class));
		int gameSlot = (int) info[2];

		try {
			game = new RemoteSpace("tcp://" + serverIP + ":9001/game" + gameSlot + "?keep");
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		game.put("testing");
	}

	public static ArrayList<Player> getPlayers() throws InterruptedException {

		game.put("GetPlayers", userID);
		Object[] tuple = lobby.get(new ActualField("PlayerListSize"), new ActualField(userID), new FormalField(Integer.class));
		Object[] qT;
		int n = (int) tuple[1];
		ArrayList<Player> ps = new ArrayList<>();
		for (int i = 0; i < n; i++) {
			qT = game.get(new ActualField("PlayerList"),
					new ActualField(userID),
					new FormalField(Player.class));
			ps.add((Player) qT[2]);
		}

		return ps;
	}

	// TODO: make a toggleReady instead?
	public static void sendReady() {

	}

	// TODO: make a toggleReady instead?
	public static void notReady() {

	}

	public static void leaveGame() {

	}

	public static void refreshPlayerList() throws InterruptedException {
		// TODO:
		// Get status
		// Get players (check if they are ready)
		Object[] tuple = lobby.get(new ActualField("lobby"),new FormalField(String.class), new FormalField(String.class), new FormalField(Integer.class));
		System.out.println("Got response: " + tuple[1]);
		if (tuple[1].equals("players")) {

		}
		else if (tuple[1].equals("voteUpdate")){

		}
		else if (tuple[1].equals("refreshGameList")) {

		}
	}
	
	private static void gameLobby() {
		// Setup of a local tuple space.
		Space local = new SequentialSpace();
		
		// Initialise two threads.
		new Thread(new Listener(local, game)).start();
		new Thread(new Talker(game, userID)).start();
		
		// TODO: Implement a system similar to the listening in server main, but from the local tuple space.
		
		Object[] tuple;
		try {
			tuple = local.get(new ActualField("local"),new FormalField(String.class), new FormalField(String.class), new FormalField(Integer.class));
			System.out.println("Local Lobby: Got response: " + tuple[1]);
	        if (tuple[1].equals("ready")) {
				// TODO: Ready button: A toggle option to be ready/not be ready.
	        	// Update personal GUI and send response to server for other players to do the same.
	        } else if (tuple[1].equals("start")){
	        	// TODO: Start game: A button for the host, possibly to entirely replace his ready button.
	        	// Starts the game, many stuff happens.
	        } else if (tuple[1].equals("leave")){
	        	// TODO: Leave game: Return the player to the main lobby, adjust tuple spaces.
	        	// Update GUI, change from game tuple space to lobby tuple space, adjust other players GUI by sending message to server.
	        } else if (tuple[1].equals("update")){
	        	// TODO: Update from the server, update relevant GUI.
	        	// Possible updates: User joins, user leaves, user checks ready, user unchecks ready.
	        }
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		
		
		
		
	}
}