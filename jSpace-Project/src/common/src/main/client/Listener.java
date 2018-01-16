package common.src.main.client;

import common.src.main.server.utilities.WhiteCard;
import org.jspace.ActualField;
import org.jspace.FormalField;
import org.jspace.RemoteSpace;

import common.src.main.server.GameSlot;

public class Listener implements Runnable{
	private static RemoteSpace game;
	private static int userID;
	private static boolean gameStarted = false;
	
	public Listener(RemoteSpace game, int userID){
		this.game = game;
		this.userID = userID;
	}
	
	public void run() {
		lobbyListener();
		if (gameStarted){
			inGameListener();
		}
		// TODO: Exit procedure when a game has ended.
	} // End of run();

	public void lobbyListener(){
		System.out.println("Listener: Now listening on Client...");
		while (true){
			try {
				// Room - Type of Action - ID - Game Slot - Chat
				Object[] tuple = game.get(new ActualField("updateLobby"),new FormalField(String.class), new ActualField(userID), new FormalField(GameSlot.class), new FormalField(String.class));
				GameSlot gameSlot = (GameSlot) tuple[3];
				System.out.println("Local Lobby: Got response: " + tuple[1]);
				if (tuple[1].equals("start")){
					// TODO: Start game: A button for the host, possibly to entirely replace his ready button.
					// Starts the game, many stuff happens.
					System.out.println("Game started! GET READY TO RUMBLE!!!!");
					Object[] gameInfo = game.get(new ActualField("starting"), new ActualField(userID),new FormalField(Integer.class));
					Client.main.startGame((int) gameInfo[2]);
					gameStarted = true;
					return;
				} else if (tuple[1].equals("update")){
					// TODO: Update from the server, update relevant GUI.
					// Occurs when a player joins/leaves/changes ready state, will fully update a specified game slot.
					System.out.println("Game updated: " + gameSlot.getName() + ", at seat " + gameSlot.getSlot() + ", is he ready: "+gameSlot.isReady());
					Client.main.updatePlayer(gameSlot);
				} else if (tuple[1].equals("leave")){
					System.out.println("You have left the game!");
					Client.talker = null;
					Client.listener = null;
					return;
				} else if (tuple[1].equals("error")){
					System.out.println("An error occured.");
					// TODO: Force an error message to pop up.
					// This happens if a player
				} else if (tuple[1].equals("chat")){
					System.out.println("hej");
					System.out.println((String) tuple[4]);
					Client.main.lobbyChat.chatMessageRecived((String) tuple[4]);
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	} // End of lobby listener.
	
	public void inGameListener(){
		while(true) {
			try {
				// talker.put("ingame", "picked", player.getId(), pickedCards[i], i);
				// String - STRING - INT - STRING - INT
				Object[] tuple = game.get(new ActualField("ingame"), new FormalField(String.class), new ActualField(userID), new FormalField(String.class), new FormalField(Integer.class));
				System.out.println("Ingame: Got response from server: " + tuple[1]);
				if (tuple[1].equals("white")) {
					Client.whiteCards[(int) tuple[4]] = (String) tuple[3];
					Client.main.setWhite((String) tuple[3], (int) tuple[4]);
				} else if (tuple[1].equals("black")) {
					Client.amountOfBlanks = (int) tuple[4];
					Client.main.setBlack((String) tuple[3]);
				}
				else if (tuple[1].equals("yourpick")) {
					// ("ingame", "yourpick", player.getId(), null, cardIndex);
					int pickedCard = (int) tuple[4];
					// TODO: Update for chosen card on Client
				}
				else if (tuple[1].equals("picked")) {
					// ("ingame", "picked", player.getId(), pickedCards[i], i);
					//Object[] pickedInfo = game.get(new ActualField("ingame"), new FormalField(String.class), new ActualField(userID), new FormalField(String[].class), new FormalField(Integer.class));
					Client.main.setSelected((int) tuple[4], (String) tuple[3]);
				}
				else if (tuple[1].equals("czar")) {
					// ("ingame", "picked", player.getId(), pickedCards[i], i);
					Client.main.setCzar((String) tuple[3]);
				}
				else if (tuple[1].equals("result")) {
					// ("ingame", "result", player.getId(), winnerCard.getSentence(), 0);
					// TODO: Show results to GUI (0 is not used)
				}
				else if (tuple[1].equals("resultPlayer")) {
					// TODO: Show results to GUI (0 is not used)
					for (int i = 0; i < 8; i++) {
						Client.main.setSelected(i, "");
					}
				}
				else if (tuple[1].equals("yourturn")) {
					//allowPlayerTurn();
					// TODO: Tell client its their turn (GUI Changes?)
				}
				// TODO: Player leaves/joins in mid-game
			}
			catch (InterruptedException e) {

			}
		}
	} // End of in game listener.
}
