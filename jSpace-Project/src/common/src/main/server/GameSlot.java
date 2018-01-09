package common.src.main.server;

public class GameSlot {

    private int slot;
    private String name;
    private boolean ready;

    public GameSlot(int slot, String name) {
        this.slot = slot;
        this.name = name;
        this.ready = false;
    }

    public void setSlot(int slot) {
        this.slot = slot;
    }

    public void setReady(boolean ready) {
        this.ready = ready;
    }

    public int getSlot() {
        return this.slot;
    }

    public String getName() {
        return this.name;
    }

    public boolean isReady() {
        return this.ready;
    }

}
