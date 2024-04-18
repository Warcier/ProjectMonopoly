package model;

import java.util.List;

public class Player {
    private String name;
    private boolean isBankrupt;
    private int cash;
    private List<Property> ownProperty;

    public Player(String name){
        this.cash = 15000;
        this.name = name;
    }

    public String getName(){
        return name;
    }

    public int getCash(){
        return cash;
    }

    public boolean isBankrupt(){
        return isBankrupt;
    }

    public void playerBankrupted() {
        this.isBankrupt = true;
    }

    public void setCash(int amount){
        this.cash = this.cash + amount;
    }

    public List<Property> getPlayerProperty(){
        return ownProperty;
    }

    public void addProperty(Property property){
        this.ownProperty.add(property);
    }

}
