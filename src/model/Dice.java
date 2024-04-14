package model;
import java.util.Random;
/**
 *
 * @author rexhe
 */
public class Dice {
    private int diceNumber;

    public Dice() {
        this.diceNumber = 0;
    }

    public int getDiceNumber() {
        return this.diceNumber;
    }

    public void roll() {
        // get random Dice Number 1 to 10 
        Random rand = new Random();
        this.diceNumber = rand.nextInt(10) + 1;
    }
}