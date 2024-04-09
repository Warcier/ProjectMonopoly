package model.list;

import model.Player;
import model.Property;

import java.util.ArrayList;
import java.util.List;

public class Node {

    private int slot;
    private Player owner;
    private Property property;
    List<Player> playersOnThisLand;

    Node next;

    public Node() {
        this.playersOnThisLand = new ArrayList<>();
    }

    public Player getOwner() {
        return owner;
    }

    public void setOwner(Player owner) {
        this.owner = owner;
    }

    public Property getProperty() {
        return property;
    }

    public void setProperty(Property property) {
        this.property = property;
    }

    public int getSlot() {
        return slot;
    }

    public void setSlot(int slot) {
        this.slot = slot;
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
