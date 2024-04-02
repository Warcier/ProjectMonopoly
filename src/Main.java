import model.Player;
import model.list.CircularLinkedList;

import java.util.ArrayList;
import java.util.List;

public class Main {


    public static void main(String[] args) {
        // Player reference
        List<Player> players = new ArrayList<>();
        CircularLinkedList list = new CircularLinkedList();

        //Init Player
        players.add(new Player("Player 1"));
        players.add(new Player("Player 2"));
        players.add(new Player("Player 3"));
        players.add(new Player("Player 4"));

        //Init Game Board
        list.createBoard();


        // Add Player to Starting Position
        List<Player> playersCopy = new ArrayList<>(players);
        list.AddPlayer(playersCopy);

        list.movePlayerToNextNode(players.get(0));
        list.movePlayerToNextNode(players.get(0));
        list.movePlayerToNextNode(players.get(0));

        list.movePlayerToNextNode(players.get(1));
        list.movePlayerToNextNode(players.get(1));
        list.movePlayerToNextNode(players.get(1));


        list.ShowAllPlayerPostion();
    }



}