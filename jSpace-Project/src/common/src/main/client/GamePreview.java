package common.src.main.client;

import common.src.main.server.Game;

public class GamePreview {

    private String gameName;
    private String gameStatus;
    private boolean hasPassword;
    private int currentPlayerSize;
    private int maxPlayerSize;
    private int id;
<<<<<<< HEAD
    		
=======
>>>>>>> fe9e8c28ca49f0c2cac5429e3f064a108302777d
    
    public GamePreview(String gameName, String gameStatus, boolean hasPassword, int currentPlayerSize, int maxPlayerSize, int id) {
        this.gameName = gameName;
        this.gameStatus = gameStatus;
        this.hasPassword = hasPassword;
        this.currentPlayerSize = currentPlayerSize;
        this.maxPlayerSize = maxPlayerSize;
        this.id = id;
    }

    public GamePreview(Game game) {
        this.gameName = game.getGameName();
        this.gameStatus = game.getStatus();
        this.hasPassword = (game.getPassword() != null);
        this.currentPlayerSize = game.getPlayers().size();
        this.maxPlayerSize = game.getMaxPlayers();
    }

    public String getGameName() {
        return this.gameName;
    }

    public String getGameStatus() {
        return this.gameStatus;
    }

    public boolean isPasswordProtected() {
        return this.hasPassword;
    }

    public boolean hasPassword() {
        return this.hasPassword;
    }

    public int getCurrentPlayerSize() {
        return this.currentPlayerSize;
    }

    public int getMaxPlayerSize() {
        return this.maxPlayerSize;
    }

    public int getId() {
        return this.id;
    }
}
