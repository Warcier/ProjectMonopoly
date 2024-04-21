package model;

import java.util.ArrayList;
import java.util.List;
import java.io.*;

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
        List<Property> properties = new ArrayList<>();
        //To Do, How to link up to file name
        String file ="C:/Users/rexhe/Desktop/COM3101/ProjectMonopoly/src/model/Property.csv";
        String line = "";
        try{
            BufferedReader br = new BufferedReader(new FileReader(file));
            while((line = br.readLine()) != null){
                String[] values = line.split(",");
                properties.add(new Property(values[0], Integer.parseInt(values[1])));
            }
        } catch(FileNotFoundException e){
            e.printStackTrace();
        }catch(IOException e){
            e.printStackTrace();

    }
        return properties;
    }


}
