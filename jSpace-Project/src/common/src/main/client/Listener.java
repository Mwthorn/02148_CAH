package common.src.main.client;

import org.jspace.ActualField;
import org.jspace.FormalField;
import org.jspace.RemoteSpace;
import org.jspace.Space;

import common.src.main.server.GameSlot;

public class Listener implements Runnable{
	private static Space local;
	private static RemoteSpace game;
	private static int userID;

	
	public Listener(Space local, RemoteSpace game, int userID){
		this.local = local;
		this.game = game;
		this.userID = userID;
	}
	
	public void run() {
		// TODO: Puts thing up into the local tuple space when recieving commands from the server.
		System.out.println("Listener");
		while (true){
			try {
				// UpdateLobby - Type of Action - UserID, gameslot to be acted upon.
				Object[] tuple = game.get(new ActualField("updateLobby"), new FormalField(String.class), new ActualField(userID), new FormalField(GameSlot.class));
				System.out.println("Listener: Got response from server: " + tuple[1]);
		        if (tuple[1].equals("start")){
		        	// TODO: Start game: A button for the host, possibly to entirely replace his ready button.
		        	// Starts the game, many stuff happens.
		        	local.put("local", "start", null);
					break;
		        } else if (tuple[1].equals("update")){
		        	// TODO: Update from the server, update relevant GUI.
		        	// Possible updates: User joins, user leaves, user checks ready, user unchecks ready.
		        	local.put("local", "update", (GameSlot) tuple[3]);
		        } else if (tuple[1].equals("leave")){
		        	local.put("local","leave", null);
		        	break;
		        }
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		while(true) {
			try {
				// STRING - INT - STRING - INT
				Object[] tuple = game.get(new FormalField(String.class), new ActualField(userID), new FormalField(String.class), new FormalField(Integer.class));
				System.out.println("Listener: Got response from server: " + tuple[0]);
				if (tuple[0].equals("white")) {
					// TODO: Add white add to Client
					local.put("game", "white", tuple[2], 0);
				} else if (tuple[0].equals("black")) {
					// TODO: Set Black card to given, and amount of blanks to give
					local.put("game", "black", tuple[2], tuple[3]);
				}
			}
			catch (InterruptedException e) {

			}
		}
	} // End of run();

}
