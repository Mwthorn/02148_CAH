package common.src.main.server;

import org.jspace.ActualField;
import org.jspace.Space;

public class Timeout implements Runnable {

    private Space local;
    private int timer;

    public Timeout(Space local, int timer) {
        this.local = local;
        this.timer = timer;
    }



    public void run() {
        boolean state = true;
        int timeout = 0;
        int timetotal = this.timer;
        System.out.println("Timeout: Started...");
        while (state) {
            try {
                Thread.sleep(1000);
                timeout++;
                java.lang.Object[] tuple = local.getp(new ActualField("Timeout") ,new ActualField("Cancel"));
                if (tuple != null) {
                    System.out.println("Timeout: skipped!");
                    state = false;
                }
                else if (timeout > timetotal) {
                    System.out.println("Timeout: TIMEOUT!!");
                    local.put("Game", "Timeout");
                    state = false;
                }
                else {
                    local.put("Game", "time");
                    int timeleft = timetotal-timeout;
                    System.out.println("Timeout: " + timeleft);
                    local.put("timer", timeleft);
                }

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        /*try {
            local.put("Game", "TimeoutFinish");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/
    }
}
