package common.src.main.shared;

import common.src.main.server.Game;

public class GamePreview {

    private String gameName;
    private String gameStatus;
    private boolean hasPassword;
    private int currentPlayerSize;
    private int maxPlayerSize;
    private int id;

    public GamePreview(Game game) {
        this.gameName = game.getGameName();
        this.gameStatus = game.getStatus();
        this.hasPassword = (game.getPassword() != null);
        this.currentPlayerSize = game.getPlayers().size();
        this.maxPlayerSize = game.getMaxPlayers();
        this.id = game.getID();
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
