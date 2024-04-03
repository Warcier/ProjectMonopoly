package model.list;

import model.Player;

import java.util.List;

public class CircularLinkedList {

    public Node head;
    public Node tail;
    private int size;
    private static final int MAX_SIZE = 22;

    /**
     * Constructor
     */
    public CircularLinkedList(){
        head = null;
        tail = null;
        size = 0;
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

        try {
            Node current = head;
            do {
                if (current.playersOnThisLand.contains(player)) {
                    return current;
                }
                current = current.next;
            } while (current != head);
            System.out.println("Player Node not Found: " + player.getName());
        } catch (Exception e) {
            System.out.println("An error occurred while trying to find the player node: " + e.getMessage());
        }

        return null;
    }

    /**
     * Add player to the starting position
     * @param players
     */
    public void AddPlayer(List<Player> players) {
        head.playersOnThisLand = players;
    }

    /**
     * Move the player to the next node
     * @param movingPlayer
     */
    public void movePlayerToNextNode(Player movingPlayer) {
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
