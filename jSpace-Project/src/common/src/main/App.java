package common.src.main;

import org.jspace.FormalField;
import org.jspace.SequentialSpace;
import org.jspace.Space;

public class App {
	
	// Think this makes a HelloWorld thingy
	public static void main(String[] argv) throws InterruptedException {
		Space inbox = new SequentialSpace();

		inbox.put("Hello Worlds!");
		Object[] tuple = inbox.get(new FormalField(String.class));
		System.out.println(tuple[0]);
		// Jonas er ikke dum, men Alex er :(
	}
}