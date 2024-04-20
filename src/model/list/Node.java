package model.list;

import model.Player;
import model.Property;

import java.util.ArrayList;
import java.util.List;

public class Node {

    private int block;
    private Player owner;
    private Property property;
    List<Player> playersOnThisLand = new ArrayList<Player>();


    Node next;
    public Node() {
        this.block = 1;
        this.playersOnThisLand = new ArrayList<>();
    }

    public void getOwner() {
        System.out.println(owner.getName());
    }

    public Property getProperty() {
        return property;
    }

    public void addPlayer(Player player) {
        playersOnThisLand.add(player);
    }

    public void removePlayer(Player player) {
        playersOnThisLand.remove(player);
    }

    public List<Player> getPlayerOnThisLand() {
        return playersOnThisLand;
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
