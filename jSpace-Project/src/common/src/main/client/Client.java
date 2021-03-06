package common.src.main.client;

import common.src.main.client.gui.MainGUI;
import common.src.main.shared.GamePreview;
import org.jspace.*;

import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JFrame;

public class Client {
	public static RemoteSpace lobby, listener, talker;
	public static int userID;
	public static String serverIP;
	public static int amountOfBlanks;
	public static int cardsPicked;
	public static String[] whiteCards = new String[10];
	public static MainGUI main;
	public static String userName;

	private static final int testNumber = 0;

    public static void main(String[] args) {
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				// your logic here
				/*
				try {
					main = new MainGUI();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}

				main.setTitle("Cards Against Humanity");
				main.setSize(1900,1000);
				main.setResizable(false);
				main.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				main.setVisible(true);
				main.setLocationRelativeTo(null);
				*/
			}
		});

		/* Login */
    	// Create login GUI and request name of user and IP to server.
    	
    	/* Connect to server using GUI info */
		try {
			main = new MainGUI();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		main.setTitle("Cards Against Humanity");
		main.setSize(1900,1000);
		main.setResizable(false);
		main.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		main.setVisible(true);
		main.setLocationRelativeTo(null);
	} // End of main function
	
	/*********************************************************************************************/
	/********************************** User/Lobby Interactions **********************************/
	/*********************************************************************************************/

	public static void loginUser(String IP, String name) throws IOException, InterruptedException {
		lobby = new RemoteSpace("tcp://" + IP + ":9001/lobby?keep");

		lobby.put("lobby","enter",name,0);

		Object[] tuple = lobby.get(new ActualField("UserID"),new ActualField(name), new FormalField(Integer.class));
		userID = (int) tuple[2];
		serverIP = IP;
		userName = name;
		System.out.println("Client was assigned ID: " + userID);
	} // End of login user  function
	
	public static void createNewGame(String name) {
		lobby.put("lobby", "createGame", name, userID);
		
		try {
			Object[] info = lobby.get(new ActualField("gameCreated"), new ActualField(userID), new FormalField(Integer.class));
			int gameSlot = (int) info[2];
			
			// Connects the host to the tuple space.
			main.clearLobby();
			System.out.println("Connecting to game with gameSlot:" + gameSlot);
			listener = new RemoteSpace("tcp://" + serverIP + ":9001/listener" + gameSlot + "?keep");
			talker = new RemoteSpace("tcp://" + serverIP + ":9001/talker" + gameSlot + "?keep");
		} catch (InterruptedException | IOException e) {
			e.printStackTrace();
		}
		gameLobby();
	} // End of createNewGame function

	public static void joinGame(int gameID) throws InterruptedException {
		System.out.println("Trying to join gameID: " + gameID);
		lobby.put("lobby", "joinGame", Integer.toString(userID), gameID);
		
		Object[] info = lobby.get(new ActualField("joinedGame"), new ActualField(userID), new FormalField(Integer.class));
		int gameSlot = (int) info[2];
		System.out.println("Connecting to game with gameSlot: " + gameSlot);
		main.clearLobby();
		try {
			listener = new RemoteSpace("tcp://" + serverIP + ":9001/listener" + gameSlot + "?keep");
			talker = new RemoteSpace("tcp://" + serverIP + ":9001/talker" + gameSlot + "?keep");
		} catch (IOException e) {
			e.printStackTrace();
		}
		gameLobby();
	} // End of joinGame function

	public static boolean joinGame(int gameID, String pass) throws InterruptedException {
		System.out.println("Trying to join gameID: " + gameID);
		lobby.put("lobby", "joinGamePass", Integer.toString(userID), gameID);
		lobby.put("lobby", "joinGameWord", userID, pass);

		Object[] check = lobby.get(new ActualField("joinedGamePassGet"), new ActualField(userID), new FormalField(Boolean.class));
		Boolean correct = (Boolean) check[2];
		if (correct) {
			Object[] info = lobby.get(new ActualField("joinedGame"), new ActualField(userID), new FormalField(Integer.class));
			int gameSlot = (int) info[2];
			System.out.println("Connecting to game with gameSlot: " + gameSlot);
			main.clearLobby();
			try {
				listener = new RemoteSpace("tcp://" + serverIP + ":9001/listener" + gameSlot + "?keep");
				talker = new RemoteSpace("tcp://" + serverIP + ":9001/talker" + gameSlot + "?keep");
			} catch (IOException e) {
				e.printStackTrace();
			}
			gameLobby();
			return true;
		}
		else {
			return false;
		}
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
	
	public static void signOut() {
		
	}// End of signOut function
	
	/*********************************************************************************************/
	/******************************* User/Game Lobby Interactions ********************************/
	/*********************************************************************************************/

	public static void gameLobby() {
		// Create game lobby GUI.

		// Setup listener.
		new Thread(new Listener(listener, userID)).start();
		
		// TODO: Implement a system similar to the listening in server main, but from the local tuple space.

		// TODO: Leave game: Return the player to the main lobby, adjust tuple spaces.
    	// Update GUI, change from game tuple space to lobby tuple space, adjust other players GUI by sending message to server.
	} // End of gameLobby function

	public static void sendReady() {
		talker.put("game", "ready", userID, "");
	}

	public static void sendLeave() {
		talker.put("game", "leave", userID, "");
	}

	public static void sendStart() {
		talker.put("game", "start", userID, "");
	}
	
	public static void sendLobbyChatMessage(String message) {
		talker.put("game", "chat", userID, message);
	}
	
	/*********************************************************************************************/
	/********************************* User/In-Game Interactions *********************************/
	/*********************************************************************************************/
	
	public static boolean pickWhiteCard(int i) {
		talker.put("gameListener", "pickWhite", userID, i);
		return true;
	}

	public static boolean pickWinnerCard(int i) {
		talker.put("gameListener", "chooseWinnerCard", userID, i);
		return true;
	}

	// public static boolean isPlayerTurn() {
	//	 return turnToPick;
	// }

	public static String[] getWhiteCards() {
		return whiteCards;
	}

	public static void sendGameChatMessage(String message) {
		System.out.println("Client trying to send message");
		talker.put("gameListener", "chat", userID, 0);
		talker.put("gameListenerChat", message, userID);
	}
	
	public static String getName() {
		return userName;
	}
}