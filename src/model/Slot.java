package com3101_project;
/**
 *
 * @author rexhe
 */
public class Slot {
    private String Slot_Name;
    private int Slot_Price;
    private Player Slot_Owner;

    public String getSlotName() {
        return Slot_Name;
    }

    public int getSlotPrice() {
        return Slot_Price;
    }

    public Player getSlotOwner(int position) {
        return Slot_Owner;
    }

    public void setSlotOwner(Player owner) {
        this.Slot_Owner = owner;
    }
}
