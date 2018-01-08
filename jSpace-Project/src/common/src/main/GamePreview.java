package common.src.main;

public class GamePreview {

    private String gameName;
    private String gameStatus;
    private boolean hasPassword;
    private int currentPlayerSize;
    private int maxPlayerSize;

    public GamePreview(String gameName, String gameStatus, boolean hasPassword, int currentPlayerSize, int maxPlayerSize) {
        this.gameName = gameName;
        this.gameStatus = gameStatus;
        this.hasPassword = hasPassword;
        this.currentPlayerSize = currentPlayerSize;
        this.maxPlayerSize = maxPlayerSize;
    }
}
