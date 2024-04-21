package model;

import java.util.ArrayList;
import java.util.List;

public class Player {
    private String name;
    private boolean isBankrupt;
    private int cash;
    boolean passedGo = false;
    private List<Property> ownProperty;

    public Player(String name){
        this.ownProperty = new ArrayList<>();
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

    public void setBankrupt(boolean isBankrupt){
        this.isBankrupt = isBankrupt;
    }

    public void addCash(int amount){
        this.cash = this.cash + amount;
    }

    public boolean getPassedGo(){
        return passedGo;
    }

    public void setPassedGo(boolean passedGo){
        this.passedGo = passedGo;
    }
    public void deductCash(int amount){
        this.cash = this.cash - amount;
    }

    public void setCash(int amount){
        this.cash = amount;
    }

    public List<Property> getPlayerProperty(){
        return ownProperty;
    }

    public void addProperty(Property property){
        this.ownProperty.add(property);
    }


}
