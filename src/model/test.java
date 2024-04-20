package model;

import java.util.ArrayList;
import java.util.List;

public class test {

    public static List<Player> initPlayer() {
        List<Player> players = new ArrayList<>();
        players.add(new Player("Player 1"));
        players.add(new Player("Player 2"));
        players.add(new Player("Player 3"));
        players.add(new Player("Player 4"));

        return players;
    }

    public static List<Property> initProperty() {
        // Implement property initialization here
        List<Property> properties = new ArrayList<>();

        properties.add(new Property("Slot 1", 1000));
        properties.add(new Property("Slot 2", 2000));
        properties.add(new Property("Slot 3", 3000));
        properties.add(new Property("Slot 4", 4000));
        properties.add(new Property("Slot 5", 5000));
        properties.add(new Property("Slot 6", 6000));
        properties.add(new Property("Slot 7", 7000));
        properties.add(new Property("Slot 8", 8000));
        properties.add(new Property("Slot 9", 9000));
        properties.add(new Property("Slot 10", 10000));
        properties.add(new Property("Slot 11", 11000));
        properties.add(new Property("Slot 12", 12000));
        properties.add(new Property("Slot 13", 13000));
        properties.add(new Property("Slot 14", 14000));
        properties.add(new Property("Slot 15", 15000));
        properties.add(new Property("Slot 16", 16000));
        properties.add(new Property("Slot 17", 17000));
        properties.add(new Property("Slot 18", 18000));
        properties.add(new Property("Slot 19", 19000));
        properties.add(new Property("Slot 20", 20000));
        properties.add(new Property("Slot 21", 21000));
        properties.add(new Property("Slot 22", 22000));
        properties.add(new Property("Slot 23", 23000));

        return properties;
    }


}
