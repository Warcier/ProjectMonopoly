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

        properties.add(new Property("GO", 0));
        properties.add(new Property("Wanchai", 1200));
        properties.add(new Property("Ping Chau", 3000));
        properties.add(new Property("Tai O", 4000));
        properties.add(new Property("Lantau", 4000));
        properties.add(new Property("Central", 3000));
        properties.add(new Property("West Kowloon", 2000));
        properties.add(new Property("Kowloon Bay", 2000));
        properties.add(new Property("City One", 2000));
        properties.add(new Property("Sha Tin", 2000));
        properties.add(new Property("Tai Po Market", 2000));
        properties.add(new Property("University", 200));
        properties.add(new Property("Sai Yin Pun", 2300));
        properties.add(new Property("Kennedy Town", 2400));
        properties.add(new Property("Chai Wan", 2800));
        properties.add(new Property("Jordan", 2600));
        properties.add(new Property("Mong Kok", 2400));
        properties.add(new Property("Tung Chung", 2300));
        properties.add(new Property("Sunny Bay", 2400));
        properties.add(new Property("Slot 20", 2500));
        properties.add(new Property("Slot 21", 2600));
        properties.add(new Property("Slot 22", 2700));
        properties.add(new Property("Slot 23", 2800));
        properties.add(new Property("Slot 24", 2900));

        return properties;
    }


}
