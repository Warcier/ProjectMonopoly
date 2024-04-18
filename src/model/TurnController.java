package model;

import model.list.CircularLinkedList;

import java.util.List;

public class TurnController {
    private List<Player> players;
    private int currentPlayerIndex;

    public TurnController() {
        this.players = CircularLinkedList.players;
        this.currentPlayerIndex = 0;
    }

    public Player getCurrentPlayer() {
        return players.get(currentPlayerIndex);
    }

    public void nextTurn() {
        if (currentPlayerIndex == players.size() - 1) {
            currentPlayerIndex = 0;
        } else {
            currentPlayerIndex++;
        }
        System.out.println("Next turn: " + getCurrentPlayer().getName());
    }

}
