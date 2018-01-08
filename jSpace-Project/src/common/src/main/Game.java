package common.src.main;

import org.jspace.SequentialSpace;
import org.jspace.SpaceRepository;

import common.src.main.cards.BlackCard;
import common.src.main.cards.WhiteCard;

import java.util.ArrayList;
import java.util.Objects;

public class Game implements Runnable {

    private String gameName;
    private String password;
    private ArrayList<WhiteCard> whiteCards;
    private ArrayList<BlackCard> blackCards;
    private ArrayList<Player> players;
    private String status;

    SpaceRepository repository = new SpaceRepository();
    SequentialSpace game = new SequentialSpace();
    SequentialSpace serverLobby;

    // TODO: Add chat?
    // TODO: Add rounds/max round?
    // TODO: Add host?
    // TODO: Add vote count?

    public Game(String gameName,
                ArrayList<WhiteCard> whiteCards,
                ArrayList<BlackCard> blackCards,
                SpaceRepository repository,
                int gameSlot,
                SequentialSpace serverLobby) {
        this.gameName = gameName;
        this.whiteCards = whiteCards;
        this.blackCards = blackCards;
        this.password = null;
        this.serverLobby = serverLobby;

        this.repository = repository;
        this.repository.add("game"+gameSlot, game);
    }

    public void run() {

    }

    public String getGameName() {
        return this.gameName;
    }

    public String getPassword() {
        return this.password;
    }

    public boolean hasPassword() {
        return this.password != null;
    }

    public ArrayList<Player> getPlayers() {
        return this.players;
    }

    public Player FindPlayer(String name) {
        for (Player player : players) {
            if (Objects.equals(player.getName(), name)) {
                return player;
            }
        }
        return null;
    }

    public String getStatus() {
        return this.status;
    }

    public void addPointsTo(Player player, int i) {
        player.addPoints(i);
    }

    public void addPlayerToGame(Player player) {
        players.add(player);
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
