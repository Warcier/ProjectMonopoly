package model.list;

import model.Player;
import model.Property;

import java.util.ArrayList;
import java.util.List;

public class Node {

    int block;
    List<Player> playersOnThisLand = new ArrayList<Player>();
    Player owner;

    Node next;
    public Node() {
        this.block = 1;
        this.playersOnThisLand = new ArrayList<>();
    }

    public void addPlayer(Player player) {
        playersOnThisLand.add(player);
    }

    public void removePlayer(Player player) {
        playersOnThisLand.remove(player);
    }

    public void getIndex(Player player) {
        System.out.println(playersOnThisLand.indexOf(player));
    }

    public void showPlayersOnThisLand() {
        for (Player player : playersOnThisLand) {
            System.out.println(player.getName());
        }
    }

}
