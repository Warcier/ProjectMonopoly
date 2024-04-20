package model.list;

import model.Player;
import model.Property;

import java.util.ArrayList;
import java.util.List;

public class CircularLinkedList {

    private static List<Player> players;
    private static List<Property> properties;

    private Node head;
    private Node tail;
    private int size;
    private static final int MAX_SIZE = 23;


    /**
     * Constructor
     */
    public CircularLinkedList(List<Player> players, List<Property> properties){
        CircularLinkedList.players = players;
        CircularLinkedList.properties = properties;
        head = null;
        tail = null;
        size = 0;
    }

    public static List<Player> getPlayers() {
        return players;
    }

    public List<Property> getProperties() {
        return properties;
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
                head.playersOnThisLand.addAll(players);
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
            System.out.println("Player is bankrupt.");
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

    public void PlayerBankrupted(Player player){
        Node current = findPlayerNode(player);
        if (current != null) {
            player.playerBankrupted();
            current.playersOnThisLand.remove(player);
        }

    }

    public void payRent(Player rentPlayer, Node node) {
        Player owner = propertyOwner(rentPlayer);

        int rentPrice = node.getProperty().getRentPrice();

        if (owner != null) {
            if (rentPlayer.getCash() < rentPrice) {
                owner.addCash(rentPlayer.getCash() + owner.getCash());
                this.PlayerBankrupted(rentPlayer);
                return;
            }
            rentPlayer.deleteCash(rentPrice);
            owner.addCash(rentPrice);
        }
    }

    public void buyProperty(Player player) {
        if (player.isBankrupt()) {
            return;
        }

        Node current = findPlayerNode(player);
        if (current != null) {
            if (current.getProperty().getLandPrice() > player.getCash()) {
                this.PlayerBankrupted(player);
                return;
            }
            player.addProperty(current.getProperty());
            player.deleteCash(current.getProperty().getLandPrice());
            current.setOwner(player);
        }
    }

    public void giveBonusGO(Player player) {
            player.addCash(2000);
    }

    public Player propertyOwner(Player player) {
        Node node = findPlayerNode(player);
        if (node.getOwner() != null) {
            return node.getOwner();
        }
        return null;
    }

    /**
     * Move the player to the next node
     */
    public void movePlayerToNextNode(Player movingPlayer, int diceNumber) {
        if (movingPlayer.isBankrupt()) {
            System.out.println("Player is bankrupt.");
            return;
        }

        Node currentNode = findPlayerNode(movingPlayer);

        if (currentNode == null) {
            System.out.println("Player not found on any node.");
            return;
        }

        // Remove the player from the current node
        currentNode.playersOnThisLand.remove(movingPlayer);

        // Move the player to the next node based on the dice number
        Node nextNode = currentNode;
        for (int i = 0; i < diceNumber; i++) {
            nextNode = nextNode.next;
            if (nextNode == head) {
                giveBonusGO(movingPlayer);
            }
        }

        // Add the player to the next node
        nextNode.playersOnThisLand.add(movingPlayer);

    }


    /**
     * Show all player position
     */
    public void ShowAllPlayerPostion(){
        Node current = head;
        do {
            System.out.println("Node position: " + current.getSlot());
            for (Player player : current.playersOnThisLand) {
                if (player != null) {
                    System.out.println("Player: " + player.getName());
                }
            }
            System.out.printf("--------------------\n");
            current = current.next;
        } while (current != head);
    }


    public Player checkWinCondition() {
        List<Player> notBankruptPlayers = new ArrayList<>();
        List<Player> playerBankrupt = new ArrayList<>();

        for (Player player : players) {
            if (!player.isBankrupt()) {
                notBankruptPlayers.add(player);
            } else {
                playerBankrupt.add(player);
            }
        }

        if (notBankruptPlayers.size() == 1) {
            return notBankruptPlayers.get(0);
        }

        return null;
    }

    public Node getSlot(int num) {
        // find Node
        if (head == null || num < 0) {
            return null; 
        }
        Node current = head;
        int count = 0;
        do {
            if (count == num) {
                return current; 
            }
            current = current.next; 
            count++;
        } while (current != head); 
    
        return null; 
    }

}
