package common.src.main.client;

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
	}

}
