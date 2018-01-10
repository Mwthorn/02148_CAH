package common.src.main.server;

import org.jspace.ActualField;
import org.jspace.FormalField;
import org.jspace.SequentialSpace;
import org.jspace.Space;

public class GameListener implements Runnable {

    public SequentialSpace game;

    public GameListener(SequentialSpace game, Space local) {
        this.game = game;
    }

    public void run() {
        Object[] tuple;
        while(true) {
            try {tuple = game.get(new ActualField("gameListener"), new FormalField(String.class), new FormalField(Integer.class));
                if (tuple[1].equals("chooseWhiteCards")) {

                }
                else if (tuple[1].equals("pickACard")) {

                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
