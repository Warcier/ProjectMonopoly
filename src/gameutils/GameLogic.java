package gameutils;

import model.Player;
import model.list.CircularLinkedList;
import model.list.Node;
import java.util.List;

public class GameLogic {

    /**
     * @param head   CircularLinkedList
     * @param player Player
     * @param distance int
     */
    public static void movePlayer(CircularLinkedList head, Player player, int distance) {
        for (int i = 0; i < distance; i++) {
            head.movePlayerToNextNode(player);
        }
    }

    /**
     * @param player Player
     * @param node Node
     */
    public static void buyProperty(Player player, Node node) {
        player.addProperty(node.getProperty());
        // Implement buy property logic here
    }


    /**
     * @param players List<Player>
     * @param node Node
     * @return boolean
     */
    public static boolean checkPropertyOwner(List<Player> players, Node node){
        for(Player player : players) {
            if (player.getPlayerProperty().isEmpty()) {
                return false;
            }
            if (player.getPlayerProperty().contains(node.getProperty())) {
                return true;
            }
        }
        return false;
    }

    //TODO: Implement the logic for the player to check win condition
    public static boolean checkWinCondition() {
        // Implement win condition check here
        return false;
    }

    //TODo: Implement the logic for the player to update players remaining
    public static void updatePlayersRemaining(List<Player> players) {
        // Update players remaining
    }

    //TODO: Implement the logic for the player to update cash
    public static void updateCash(int score) {
        // Update highest score
    }

    //TODO: Implement the logic for the player to pay rent
    public static void payRent(Player player, Node node) {
        // Implement pay rent logic here
    }

    // Add more game logic methods as needed
}
