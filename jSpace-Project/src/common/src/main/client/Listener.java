package common.src.main.client;

import org.jspace.ActualField;
import org.jspace.FormalField;
import org.jspace.RemoteSpace;
import org.jspace.Space;

public class Listener implements Runnable{
	private static Space local;
	private static RemoteSpace game;

	
	public Listener(Space local, RemoteSpace game){
		this.local = local;
		this.game = game;
	}
	
	public void run() {
		// TODO: Puts thing up into the local tuple space when recieving commands from the server.
		while (true){
			try {
				Object[] tuple = game.get(new ActualField("updateLocal"));
				System.out.println("Listener: Got response from server: " + tuple[1]);
		        if (tuple[1].equals("ready")) {
					// TODO: Ready button: A toggle option to be ready/not be ready.
		        	// Update personal GUI and send response to server for other players to do the same.
		        	local.put("local", "ready");
		        } else if (tuple[1].equals("start")){
		        	// TODO: Start game: A button for the host, possibly to entirely replace his ready button.
		        	// Starts the game, many stuff happens.
		        } else if (tuple[1].equals("update")){
		        	// TODO: Update from the server, update relevant GUI.
		        	// Possible updates: User joins, user leaves, user checks ready, user unchecks ready.
		        } else if (tuple[1].equals("leave")){
		        	break;
		        }
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}
