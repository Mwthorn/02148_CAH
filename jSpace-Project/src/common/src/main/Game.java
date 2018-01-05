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
    private WhiteCard[] whiteCards;
    private BlackCard[] blackCards;
    private ArrayList<Player> players;
    private String status;

    SpaceRepository repository = new SpaceRepository();
    SequentialSpace game = new SequentialSpace();

    // TODO: Add chat?
    // TODO: Add rounds/max round?
    // TODO: Add host?
    // TODO: Add vote count?

    public Game(String gameName,
                WhiteCard[] whiteCards,
                BlackCard[] blackCards,
                SpaceRepository repository,
                int gameSlot) {
        this.gameName = gameName;
        this.whiteCards = whiteCards;
        this.blackCards = blackCards;
        this.password = null;
        this.repository = repository;
        this.repository.addGate("tcp://127.0.0.1:9001/?keep");
        this.repository.add("game"+1, game);
    }

    public Game(SpaceRepository repository, int gameSlot) {
        this.repository = repository;
        this.repository.addGate("tcp://127.0.0.1:9001/?keep");
        this.repository.add("game"+1, game);
    }

    public void run() {

    }

    public Game(String gameName,
                WhiteCard[] whiteCards,
                BlackCard[] blackCards,
                String password,
                SpaceRepository repository,
                int gameSlot) {
        this.gameName = gameName;
        this.whiteCards = whiteCards;
        this.blackCards = blackCards;
        this.password = password;
        this.repository = repository;
        this.repository.addGate("tcp://127.0.0.1:9001/?keep");
        this.repository.add("game"+1, game);
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
