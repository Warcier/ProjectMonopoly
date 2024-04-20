import java.util.Random;
/**
 *
 * @author rexhe
 */
public class Dice {
    private int Dice_Number;

    public int getDiceNumber() {
        // get random Dice Number 1 to 10 
        Random rand = new Random();
        Dice_Number = rand.nextInt(10) + 1;
        return Dice_Number;
    }
}