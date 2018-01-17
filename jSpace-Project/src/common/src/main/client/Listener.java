package common.src.main.client;

import common.src.main.gui.MainGUI;
import common.src.main.server.utilities.WhiteCard;
import org.jspace.ActualField;
import org.jspace.FormalField;
import org.jspace.RemoteSpace;

import common.src.main.server.GameSlot;

import static common.src.main.gui.MainGUI.phases.*;

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

	public enum phase {WAIT, WHITE, WAITCZAR, CZAR, WINNER}

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
					Client.main.chatLobbyMessageRecived((String) tuple[4]);
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
					for (int i = 0; i < 8; i++) {
						for (int j = 0; j < 3; j++) {
							Client.main.setSelected(i, j, "");
						}
						Client.main.highlightWinner(i,false);
					}
					Client.cardsPicked = 0;
				}
				else if (tuple[1].equals("yourpick")) {
					// ("ingame", "yourpick", player.getId(), null, cardIndex);
					int pickedCard = (int) tuple[4];
					// TODO: Update for chosen card on Client
					Client.main.playerButton(false, pickedCard);
					Client.cardsPicked++;
					if (Client.cardsPicked >= Client.amountOfBlanks) {
						Client.main.setPhase(WAIT);
						for (int i = 0; i < 10; i++) {
							Client.main.playerButton(false, i);
						}
					}
				}
				else if (tuple[1].equals("picked")) {
					// ("ingame", "picked", player.getId(), pickedCards[i], i);
					Object[] pickedInfo = game.get(new ActualField("cardPicked"), new ActualField(userID), new FormalField(Integer.class));
					Client.main.setSelected((int) tuple[4], (int) pickedInfo[2], (String) tuple[3]);
					if (!Client.main.isCzar) {
						Client.main.setPhase(WAITCZAR);
					}
					for (int i = 0; i < 10; i++) {
						Client.main.playerButton(false, i);
					}
				}
				else if (tuple[1].equals("czar")) {

					// ("ingame", "picked", player.getId(), pickedCards[i], i);
					// Client.main.setCzar((String) tuple[3]);
					Client.main.setPhase(WAIT);
					for (int i = 0; i < 10; i++) {
						Client.main.playerButton(false, i);
					}
					Client.main.setRound((int) tuple[4]);
				}
				else if (tuple[1].equals("result")) {
					// ("ingame", "result", player.getId(), winnerCard.getSentence(), 0);
					// TODO: Show results to GUI (0 is not used)
				}
				else if (tuple[1].equals("resultPlayer")) {
					// TODO: Show results to GUI (0 is not used)
					Client.main.highlightWinner((int) tuple[4], true);
					Client.main.setPhase(WINNER);
					for (int i = 0; i < 8; i++) {
						Client.main.czarButton(false, i);
					}
				}
				else if (tuple[1].equals("yourturn")) {
					//allowPlayerTurn();
					Client.main.setPhase(PICK);
					Client.main.setCzar(false);
					for (int i = 0; i < 10; i++) {
						Client.main.playerButton(true, i);
					}
				}
				else if (tuple[1].equals("czarturn")) {
					//allowPlayerTurn();
					Client.main.setPhase(CZAR);
					for (int i = 0; i < (int) tuple[4]; i++) {
						Client.main.czarButton(true, i);
					}
				}
				else if (tuple[1].equals("youczar")) {
					//allowPlayerTurn();
					Client.main.setCzar(true);
					Client.main.setPhase(WAIT);
				}
				else if (tuple[1].equals("timer")) {
					Client.main.setTime((int) tuple[4]);
				} else if (tuple[1].equals("points")){
					int updateSlot = Integer.parseInt((String) tuple[3]);
					System.out.println("Point player number: "+updateSlot);
					int points = (int) tuple[4];
					Client.main.setScore(updateSlot, points);
				} else if (tuple[1].equals("chat")){
					Client.main.chatLobbyMessageRecived((String) tuple[3]);
				}
				// TODO: Player leaves/joins in mid-game
			}
			catch (InterruptedException e) {

			}
		}
	} // End of in game listener.
}
