package common.src.main.server;

import org.jspace.ActualField;
import org.jspace.FormalField;
import org.jspace.SequentialSpace;
import org.jspace.Space;

public class GameListener implements Runnable {

    public SequentialSpace game;
    public Space local;

    public GameListener(SequentialSpace game, Space local) {
        this.game = game;
        this.local = local;
    }

    public void run() {
        Object[] tuple, cTuple;
        while(true) {
            // game.put("gameListener", "pickWhite", userID, i);
            try {
                tuple = game.get(new ActualField("gameListener"), new FormalField(String.class), new FormalField(Integer.class), new FormalField(Integer.class));
                System.out.println("Gamelistener got message:" + tuple[1]);
                if (tuple[1].equals("pickWhite")) {
                    local.put("Game","PickedCard");
                    local.put("Card",(int) tuple[2],(int) tuple[3]);
                }
                else if (tuple[1].equals("chooseWinnerCard")) {
                    local.put("Game","PickedWinner");
                    local.put("Card", (int) tuple[2], (int) tuple[3]);
                }
                else if (tuple[1].equals("chat")) {
                    cTuple = game.get(new ActualField("gameListenerChat"), new FormalField(String.class), new FormalField(Integer.class));
                    local.put("Chat",(String) cTuple[1]);
                    local.put("ChatSender",(int) (tuple[2]));
                    System.out.println("Got chat message: " + tuple[2] + " >> " + cTuple[1]);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
