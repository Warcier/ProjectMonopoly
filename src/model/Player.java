package model;

import java.util.List;

public class Player {
    private String Name;
    private List<Property> ownProperty;

    public Player(String name){
        this.Name = name;
    }

    public String getName(){
        return Name;
    }

    public List<Property> getPlayerProperty(){
        return ownProperty;
    }

    public void addProperty(Property property){
        this.ownProperty.add(property);
    }

    public void clearProperty(){
        this.ownProperty.clear();
    }
}
