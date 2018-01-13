package common.src.main.server;

import org.jspace.ActualField;
import org.jspace.Space;

public class Timeout implements Runnable {

    Space local;

    public Timeout(Space local) {
        this.local = local;
    }



    public void run() {
        boolean state = true;
        int timeout = 0;
        System.out.println("Timeout: Started...");
        while (state) {
            try {
                Thread.sleep(1000);
                timeout++;
                System.out.println("Timeout: " + timeout);
                if (timeout > 10) {
                    System.out.println("Timeout: TIMEOUT!!");
                    local.put("Game", "Timeout");
                    state = false;
                }
                else {
                    java.lang.Object[] tuple = local.getp(new ActualField("Timeout") ,new ActualField("Cancel"));
                    if (tuple != null) {
                        System.out.println("Timeout: skipped!");
                        state = false;
                    }
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        try {
            local.put("Game", "TimeoutFinish");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
