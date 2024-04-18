package model.list;

import model.Player;
import model.Property;

import java.util.List;

public class CircularLinkedList {

    public static List<Player> players;
    public static List<Property> properties;

    public Node head;
    public Node tail;
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

    public List<Player> getPlayers() {
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
                head.playersOnThisLand = players;
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

    public void isPlayerBankrupt(Player player) {
        if (player.getCash() <= 0) {
            player.playerBankrupted();
            removePlayers(player);
        }
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

    public void buyProperty(Player player) {
        Node current = findPlayerNode(player);
        if (current != null) {
            if (current.getProperty().getLandPrice() > player.getCash()) {
                this.removePlayers(player);
                return;
            }
            player.addProperty(current.getProperty());
            current.setOwner(player);
        }
    }

    public void giveBonusGO(Player player) {
            player.setCash(2000);
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


    public void removePlayers(Player player) {
        // Update players remaining
        this.players.remove(player);
    }




    public void getNodeInfo(Node node) {
        System.out.println("Slot: " + node.getSlot());
        System.out.println("Property: " + node.getProperty().getLandName());
        System.out.println("Owner: " + node.getOwner());
        System.out.println("Rent Price: " + node.getProperty().getRentPrice());
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


    public boolean checkWinCondition() {
        return players.size() == 1;
    }

    public Node getNode(int num) {  
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
