package common.src.main.shared;

public class GameSlot {

    private int slot;
    private int inSlot;
    private String name;
    private boolean ready;
    private boolean hasPlayer;

    public GameSlot(int slot, String name, boolean hasPlayer) {
        this.slot = slot;
        this.name = name;
        this.hasPlayer = hasPlayer;
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
    
    public boolean hasPlayer(){
    	return hasPlayer;
    }
    
    public void setSlot(boolean choice){
    	this.hasPlayer = choice;
    }
    
    public void setInSlot(int i){
    	this.inSlot = i;
    }
    
    public int getInSlot(){
    	return this.inSlot;
    }
}
