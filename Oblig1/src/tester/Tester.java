package tester;

import boardGame.Board;
import boardGame.Dice;
import boardGame.Player;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class Tester {

    private static Board board;
    private static Dice dice;
    private static Player player;

    /***
     * Creates new object before each test.
     */
    @BeforeEach
    public void initialiser() {
        board = new Board(2);
        dice = new Dice();
        player = new Player("test");
    }

    /***
     * Tests the player gets moved to the right square.
     * @result the position will be updated and is equal to the tested position.
     */
    @Test
    public void movePlayerTester() {
        player.setPosition(69);
        int diceRoll = dice.rollDice();
        board.movePlayer(diceRoll, player);
        Assertions.assertEquals(diceRoll + 69 , player.getPosition());
    }
    /***
     * Tests the player gets moved to the right square when using the ladder on square 28.
     * @result the position will be updated and is equal to the tested position.
     */
    @Test
    public void movePlayerOnLadderTester() {
        player.setPosition(25);
        int diceRoll = 3;
        board.movePlayer(diceRoll, player);
        Assertions.assertEquals(84 , player.getPosition());
    }
    /***
     * Finds the first digit on a two digit number.
     * @result checks that the first digit is equal.
     */
    @Test
    public void testFindTens() {
        int num = 39;
        Assertions.assertEquals(3, board.findTens(39));
    }
    /***
     * Starts a game of board.
     * @result
     */
    @Test
    public void testRun() {
        Assertions.assertNotNull(board.run());
    }
}
