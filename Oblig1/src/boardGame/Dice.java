package boardGame;
/***
 * Represents a
 * @author Simon Kobbenes, Matias Vedeler, Christian Evensen
 */
public class Dice {
    /***
     * creates a dice.
     */
    public Dice(){};

    /***
     * Gives a random number between 1-6.
     * @return An integer between 1-6.
     */
    public int rollDice() {
       return (int) (Math.random()*6) + 1;
    }

}
