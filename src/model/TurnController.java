package model;

import java.util.List;

public class TurnController {
    private List<Player> players;
    private int currentPlayerIndex;

    public TurnController(List<Player> players) {
        this.players = players;
        this.currentPlayerIndex = 0;
    }

    public Player getCurrentPlayer() {
        return players.get(currentPlayerIndex);
    }

    public void nextTurn() {
        currentPlayerIndex = (currentPlayerIndex + 1) % players.size();
    }

}
