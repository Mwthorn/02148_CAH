package common.src.main.client;

import common.src.main.gui.MainGUI;
import common.src.main.server.Game;
import common.src.main.server.GameSlot;
import common.src.main.server.Player;
import common.src.main.server.utilities.WhiteCard;
import org.jspace.*;

import java.io.IOException;
import java.net.UnknownHostException;
import java.util.ArrayList;

import javax.swing.JFrame;

public class Client {
	private static RemoteSpace lobby, game;
	private static int userID;
	private static String serverIP;
	public static int amountOfBlanks;
	public static boolean turnToPick;
	public static String[] whiteCards = new String[10];

	private static final int testNumber = 0;

    public static void main(String[] args) throws InterruptedException {
    	/* Login */
    	// Create login GUI and request name of user and IP to server.
    	
    	/* Connect to server using GUI info */
    	MainGUI main = new MainGUI();

		main.setTitle("Cards Against Humanity");
		main.setSize(1900,1000);
		main.setResizable(true);
		main.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		main.setVisible(true);
		main.setLocationRelativeTo(null);
		/*
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
			}*/
	} // End of main function
	
	/*********************************************************************************************/
	/********************************** User/Lobby Interactions **********************************/
	/*********************************************************************************************/

	public static void loginUser(String IP, String name) throws IOException, InterruptedException {
		// TODO: The two lines below assigning IP and name should be retrieved
		// when first signing in to the lobby.
		// serverIP = "127.0.0.1";
		// name = "Alex";
		System.out.println(IP);
		System.out.println(name);
		lobby = new RemoteSpace("tcp://" + IP + ":9001/lobby?conn");

		//lobby.put("test");
		lobby.put("lobby","enter",name,0);

		Object[] tuple = lobby.get(new ActualField("UserID"),new ActualField(name), new FormalField(Integer.class));
		userID = (int) tuple[2];
		serverIP = IP;
		System.out.println("Client was assigned ID: " + userID);
	} // End of login user  function
	
	public static void createNewGame(String name) {
		lobby.put("lobby", "createGame", name, userID);
		
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
		System.out.println(userID);
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
	
	public static void signOut() {
		
	}// End of signOut function
	
	/*********************************************************************************************/
	/******************************* User/Game Lobby Interactions ********************************/
	/*********************************************************************************************/

	private static void gameLobby() {
		// Create game lobby GUI.

		// Setup listener.
		new Thread(new Listener(game, userID)).start();
		
		// TODO: Implement a system similar to the listening in server main, but from the local tuple space.
		

		
		// TODO: Leave game: Return the player to the main lobby, adjust tuple spaces.
    	// Update GUI, change from game tuple space to lobby tuple space, adjust other players GUI by sending message to server.
	} // End of gameLobby function

	public static void sendReady() {
		System.out.println("Sending ready client...");
		game.put("game", "ready", userID);
	}

	public static void sendLeave() {
		game.put("game", "leave", userID);
	}

	public static void sendStart() {
		game.put("game", "start", userID);
	}
	
	/*********************************************************************************************/
	/********************************* User/In-Game Interactions *********************************/
	/*********************************************************************************************/
	
	public static boolean pickWhiteCard(int i) {
		if (!turnToPick) {
			return false;
		}
		game.put("gameListener", "pickWhite", userID, i);
		// game.put("pickwhite", userID, i);
		turnToPick = false;
		return true;
	}

	public static boolean pickWinnerCard(int i) {
		if (!turnToPick) {
			return false;
		}
		game.put("gameListener", "chooseWinnerCard", userID, i);
		turnToPick = false;
		return true;
	}

	public static void allowPlayerTurn() {
		turnToPick = true;
	}

	public static boolean isPlayerTurn() {
		return turnToPick;
	}

	public static String[] getWhiteCards() {
		return whiteCards;
	}

	public static void sendChatMessage(String message) {
		game.put("gameListener", "chat", userID, 0);
		game.put("gameListenerChat", message, userID);
		// TODO: Send message to all players in game class through tuple
	}

}