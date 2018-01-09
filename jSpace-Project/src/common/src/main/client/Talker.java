package common.src.main.client;

import org.jspace.RemoteSpace;
import org.jspace.Space;

public class Talker implements Runnable {
	private RemoteSpace game;
	private static int id;

	public Talker(RemoteSpace game, int userID){
		this.game = game;
		this.id = userID;
	}
	
	public void run() {
		// TODO: listen for button pushing client-side.
		
		
		int buttonPressed = 0;
		if (buttonPressed == 0){ //ready button clicked
			game.put("game", "ready", id);
		} else if (buttonPressed == 1){ // leave button clicked
			game.put("game", "leave", id);
		} else if (buttonPressed == 2){ // start game buttton clicked
			game.put("game", "start", id);
		}
	}

}