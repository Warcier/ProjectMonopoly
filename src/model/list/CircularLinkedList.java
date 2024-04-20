package model.list;

import model.Player;
import model.Property;

import java.util.List;

public class CircularLinkedList {

    List<Player> players;
    List<Property> properties;

    public Node head;
    public Node tail;
    private int size;
    private static final int MAX_SIZE = 23;

    /**
     * Constructor
     */
    public CircularLinkedList(List<Player> players, List<Property> properties){
        this.players = players;
        this.properties = properties;
        head = null;
        tail = null;
        size = 0;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public List<Property> getProperties() {
        return properties;
    }

    public void isPlayerBankrupt(Player player) {
        if (player.getCash() <= 0) {
            player.playerBankrupted();
            removePlayers(player);
        }
    }

    /**
     * Create the board
     */
    public void createBoard(){
        for (int i = 0; i < MAX_SIZE + 1; i++) {
            if (size >= MAX_SIZE) {
                return;
            }

            Node newNode = new Node();
            newNode.setProperty(properties.get(i));
            newNode.setSlot(i);
            if (head == null) {
                head = newNode;
                tail = newNode;
                head.next = head;
            } else {
                tail.next = newNode;
                tail = newNode;
                tail.next = head;
            }

            // Increase the size of the list
            size++;
        }
    }

    /**
     * Find the player node
     * @param player
     * @return
     */
    public Node findPlayerNode(Player player) {

        if (player.isBankrupt()) {
            return null;
        }

        try {
            Node current = head;
            do {
                if (current.playersOnThisLand.contains(player)) {
                    return current;
                }
                current = current.next;
            } while (current != head);
        } catch (Exception e) {
            System.out.println("An error occurred while trying to find the player node: " + e.getMessage());
        }

        return null;
    }


    public void buyProperty(Player player) {
        Node current = findPlayerNode(player);
        if (current != null) {
            player.addProperty(current.getProperty());
            current.setOwner(player);
        }
    }

    public void giveBonusGO(Player player) {
        Node current = findPlayerNode(player);
        if (current == head) {
            player.setCash(2000);
        }
    }

    public boolean isPropertyOwned(Node node) {
        return node.getOwner() != null;
    }

    public Player propertyOwner(Node node) {
        if (node.getOwner() != null) {
            return node.getOwner();
        }
        return null;
    }

    public void payRent(Player rentPlayer, Node node) {
        Player owner = propertyOwner(node);
        int rentPrice = node.getProperty().getRentPrice();

        if (owner != null) {
            rentPlayer.setCash(rentPlayer.getCash() - rentPrice);
            owner.setCash(owner.getCash() + rentPrice);
        }

        isPlayerBankrupt(rentPlayer);
    }

    public void checkIfBuyOrPayRent(Player player, Node node) {
        if (isPropertyOwned(node)) {
            payRent(player, node);
        } else {
            buyProperty(player);
        }
    }

    /**
     * Move the player to the next node
     */
    public void movePlayerToNextNode(Player movingPlayer, int diceNumber) {
        for (int i = 0; i < diceNumber; i++) {
            try {
                Node current = head;
                do {
                    if (current.playersOnThisLand.contains(movingPlayer)) {
                        current.playersOnThisLand.remove(movingPlayer);
                        current.next.playersOnThisLand.add(movingPlayer);
                        return;
                    }
                    current = current.next;
                } while (current != head);
            } catch (Exception e) {
                System.out.println("An error occurred while trying to move the player: " + e.getMessage());
            }
        }

    }


    public void removePlayers(Player player) {
        // Update players remaining
        this.players.remove(player);
    }


    /**
     * Show all player position
     */
    public void ShowAllPlayerPostion(){
        Node current = head;
        int counter = 0;
        do {
            if (current.playersOnThisLand != null && !current.playersOnThisLand.isEmpty()){
                for (Player player : current.playersOnThisLand) {
                    System.out.println("====================================");
                    System.out.println("Player: " + player.getName());
                    System.out.println("Player is on land: " + counter);
                    System.out.println("====================================");
                }
            }
            current = current.next;
            counter++;
        } while (current != head);
    }


}
