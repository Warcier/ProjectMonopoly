package model;

import model.list.CircularLinkedList;
import model.list.Node;

public class Editor {
    private CircularLinkedList board;
    private Player currentPlayer;

    public Editor(CircularLinkedList board, TurnController turnController) {
        this.board = board;
        this.currentPlayer = turnController.getCurrentPlayer();
    }

    // Modify the ownership of a land slot
    public void modifyLandOwnership(int slot, Player newOwner) {
        Node node = board.getSlot(slot);
        if (node != null) {
            node.setOwner(newOwner);
        }
    }

    // Modify the balance of a player
    public void modifyPlayerBalance(Player player, int newBalance) {
        player.setCash(newBalance);
    }

    // Modify the location of a player
    public void modifyPlayerLocation(Player player, int newLocation) {
        Node currentNode = board.findPlayerNode(player);
        if (currentNode != null) {
            currentNode.getPlayerOnThisLand().remove(player);
        }

        Node newNode = board.getSlot(newLocation);
        if (newNode != null) {
            newNode.getPlayerOnThisLand().add(player);
        }
    }

    // Modify the status of a player (active or bankrupt)
    public void modifyPlayerStatus(Player player, boolean isBankrupt) {
        player.setBankrupt(isBankrupt);
    }

    // Modify who’s turn is it to move.
    public void modifyCurrentPlayer(Player newCurrentPlayer) {
        this.currentPlayer = newCurrentPlayer;
    }

}
