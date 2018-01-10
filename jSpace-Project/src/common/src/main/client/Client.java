package common.src.main.client;

import common.src.main.server.Game;
import common.src.main.server.GameSlot;
import common.src.main.server.Player;
import org.jspace.*;

import java.io.IOException;
import java.net.UnknownHostException;
import java.util.ArrayList;

public class Client {
	private static RemoteSpace lobby, game;
	private static int userID;
	private static String serverIP;
	private static String name;
	private static int amountOfBlanks;

	private static final int testNumber = 1;

    public static void main(String[] args) {
    	/* Login */
    	// Create login GUI and request name of user and IP to server.
    	
    	/* Connect to server using GUI info */
		
			try {
				
				// Initialize tests to game lobby.
				if (testNumber == 0) {
					System.out.println("Trying to create game");
					loginUser("127.0.0.1", "Alex");
					createNewGame();
				}
				else if (testNumber == 1) {
					loginUser("127.0.0.1", "Mathias");
					ArrayList<GamePreview> gp = getGameList();
					joinGame(gp.get(0).getId());
				}
				else if (testNumber == 2) {
					loginUser("127.0.0.1", "Jonas");
					ArrayList<GamePreview> gp = getGameList();
					joinGame(gp.get(0).getId());
				}
				

			} catch (IOException | InterruptedException e) {
				e.printStackTrace();
			}
	} // End of main function
	
	/*********************************************************************************************/
	/********************************** User/Lobby Interactions **********************************/
	/*********************************************************************************************/
	
	private static void lobby(int buttonPressed, int gameID) throws InterruptedException{
		// Create lobby GUI.
		
		if (buttonPressed == 0){ // Join game button clicked
			joinGame(gameID);
		} else if (buttonPressed == 1){ // Create game button clicked
			createNewGame();
		} else if (buttonPressed == 2){ // Refresh game list buttton clicked
			getGameList();
		} else if (buttonPressed == 3){ // Sign out buttton clicked
			signOut();
		}
	} // End of lobby function

	public static void loginUser(String IP, String name) throws IOException, InterruptedException {
		// TODO: The two lines below assigning IP and name should be retrieved
		// when first signing in to the lobby.
		// serverIP = "127.0.0.1";
		// name = "Alex";
		
		lobby = new RemoteSpace("tcp://" + IP + ":9001/lobby?keep");

		//lobby.put("test");
		lobby.put("lobby","enter",name,0);

		Object[] tuple = lobby.get(new ActualField("UserID"),new ActualField(name), new FormalField(Integer.class));
		userID = (int) tuple[2];
		serverIP = IP;
		System.out.println("Client was assigned ID: " + userID);
	} // End of login user  function
	
	private static void createNewGame() {
		lobby.put("lobby", "createGame", "no nobs plx", userID);
		
		try {
			System.out.println("Trying to recieve info");
			Object[] info = lobby.get(new ActualField("gameCreated"), new ActualField(userID), new FormalField(Integer.class));
			int gameSlot = (int) info[2];
			
			// Connects the host to the tuple space.
			game = new RemoteSpace("tcp://" + serverIP + ":9001/game" + gameSlot + "?keep");
			
			game.put("testing");
			gameLobby();
		} catch (InterruptedException | IOException e) {
			e.printStackTrace();
		}
	} // End of createNewGame function

	public static void joinGame(int gameID) throws InterruptedException {
		System.out.println("Trying to join gameID: " + gameID);
		lobby.put("lobby", "joinGame", Integer.toString(userID), gameID);
		
		Object[] info = lobby.get(new ActualField("joinedGame"), new ActualField(userID), new FormalField(Integer.class));
		int gameSlot = (int) info[2];

		try {
			game = new RemoteSpace("tcp://" + serverIP + ":9001/game" + gameSlot + "?keep");
		} catch (IOException e) {
			e.printStackTrace();
		}

		game.put("testing");
		gameLobby();
	} // End of joinGame function
	
	public static ArrayList<GamePreview> getGameList() throws InterruptedException {
		lobby.put("lobby", "refreshGameList", "", userID);

		Object[] tuple = lobby.get(new ActualField("GameListSize"), new ActualField(userID), new FormalField(Integer.class));
		Object[] gT;
		int n = (int) tuple[2];
		ArrayList<GamePreview> games = new ArrayList<>();
		System.out.println("Got " + n + " games from server!");
		for (int i = 0; i < n; i++) {
			gT = lobby.get(new ActualField("GameList"),
					new ActualField(userID),
					new FormalField(GamePreview.class));
			games.add((GamePreview) gT[2]);
		}
		return games;
	} // end of getGameList function

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
	} // End of getPlayers function

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
	} // End of refreshPlayerList function
	
	private static void signOut() {
		
	}// End of signOut function
	
	/*********************************************************************************************/
	/******************************* User/Game Lobby Interactions ********************************/
	/*********************************************************************************************/

	private static void gameLobby() {
		// Create game lobby GUI.
		
		// Setup of a local tuple space.
		Space local = new SequentialSpace();
		
		// Setup listener.
		new Thread(new Listener(local, game, userID)).start();
		
		// TODO: Implement a system similar to the listening in server main, but from the local tuple space.
		
		Object[] tuple;
		while (true){
			try {
				tuple = local.get(new ActualField("local"),new FormalField(String.class), new FormalField(GameSlot.class));
				GameSlot gameSlot = (GameSlot) tuple[2];
				System.out.println("Local Lobby: Got response: " + tuple[1]);
		        if (tuple[1].equals("start")){
		        	// TODO: Start game: A button for the host, possibly to entirely replace his ready button.
		        	// Starts the game, many stuff happens.
		        	System.out.println("Game started! GET READY TO RUMBLE!!!!");
		        } else if (tuple[1].equals("update")){
		        	// TODO: Update from the server, update relevant GUI.
		        	// Occurs when a player joins/leaves/changes ready state, will fully update a specified game slot.
					System.out.println("Game updated: " + gameSlot.getName() + ", is he ready: "+gameSlot.isReady());
		        } else if (tuple[1].equals("leave")){
		        	// Call the lobby function.
		        	System.out.println("You have left the game! nob.");
		        	break;
		        }
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		// TODO: Leave game: Return the player to the main lobby, adjust tuple spaces.
    	// Update GUI, change from game tuple space to lobby tuple space, adjust other players GUI by sending message to server.
	} // End of gameLobby function

	public static void sendReady() {
		game.put("game", "ready", userID);
	}

	public static void sendLeave() {
		game.put("game", "leave", userID);
	}

	public static void sendStart() {
		game.put("game", "start", userID);
	}

	private static void talker (int buttonPressed){
		if (buttonPressed == 0){ //ready button clicked
			game.put("game", "ready", userID);
		} else if (buttonPressed == 1){ // start button clicked
			game.put("game", "start", userID);
		} else if (buttonPressed == 2){ // leave game buttton clicked
			game.put("game", "leave", userID);
		}
	} // End of talker function
	
	/*********************************************************************************************/
	/********************************* User/In-Game Interactions *********************************/
	/*********************************************************************************************/
	
	public static void cardsAgainstHumanity(){
		// TODO: Create in-game GUI.
		// TODO: This is the main communication channel for when inside the game.
	}
	
}