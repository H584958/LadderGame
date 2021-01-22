package boardGame;

import java.util.*;

/***
 * Represents a LadderGame
 * @author Simon Kobbenes, Matias Vedeler, Christian Evensen
 */
public class Board {
    private int[][] ladders;
    private int[][] snakes;
    private String[][] board;
    private List<Player> players;
    private int numPlayers;

    /***
     * A constructor for Board. It creates a 10x10 board with ladders and snakes,
     * with a specified amount of players.
     * @param numPlayers - how many players who is playing.
     */
    public Board(int numPlayers) {
        board = new String[10][10];
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                board[i][j] = " ";
            }
        }

        players = new ArrayList<>();

        String[] names = "QWER".split("");
        for (int i = 0; i < numPlayers; i++) {
            players.add(new Player(names[i]));
        }

        ladders = new int[][]{{1, 34}, {8, 12}, {28, 84}, {50, 72}};
        snakes = new int[][]{{80, 69}, {25, 13}, {43, 20}, {95, 85}};

    }

    /***
     * Prints out a representation of the current board.
     * the first square is in bottom left and last square is top left.
     */
    public void printBoard() {
        int num = 1;
        for (int j = 9; j>=0; j--) {
            if (num % 2 != 0) {
                for (int i = 9; i >= 0; i--) {
                    System.out.print("[" + ((board[j][i]) + "] "));
                }
            } else {
                for (int i = 0; i < 10; i++) {
                    System.out.print("[" + ((board[j][i]) + "] "));
                }
            }
            num++;
            System.out.println();
        }
        System.out.println();
    }

    /***
     * Updates the board to the current position of the ladders, snakes and players.
     * It visualises two or more players on the same square.
     */
    public void updateBoard() {
        int pos;
        int tens;
        int ones;

        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                board[i][j] = " ";
            }
        }

        for (int [] l : ladders) {
            pos = l[0];
            tens = findTens(pos);
            ones = pos%10;
            board[tens][ones] = "L";
        }

        for (int [] s : snakes) {
            pos = s[0];
            tens = findTens(pos);
            ones = pos%10;
            board[tens][ones] = "S";
        }

        for ( Player player : players) {
            if (player.getPosition() != -1) {
                pos = player.getPosition();
                tens = findTens(pos);
                ones = pos%10;
                if (board[tens][ones].equals(" ") || board[tens][ones].equals("L") || board[tens][ones].equals("S")) {
                    board[tens][ones] = player.getId();
                } else {
                    board[tens][ones] = board[tens][ones] + player.getId();
                }
            }
        }
    }

    /***
     * Used to get the row for ladders, snakes and players in the board Array.
     * It finds the first of the possible two digit number and returns it.
     * @param pos A number that indicates the position of the object.
     * @return A integer representing the row in the board Array.
     */
    public int findTens ( int pos) {
        int tens = 0;
        if (pos % 10 == pos) {
            tens = 0;
        } else {
            tens = pos / 10;
        }
        return tens;
    }

    /***
     * Updates the players position given its position and current diceroll. If it lands on a snake
     * or a ladder, the player will be moved to the corresponding square
     * where the ladder or snakes takes the player.
     * @param diceRoll The number of the dice roll.
     * @param p The player which is to be moved
     */
    public void movePlayer(int diceRoll, Player p) {
        int moveToPos = diceRoll + p.getPosition();

        if (moveToPos > 99) {
            return;
        }

        for (int[] l : ladders) {
            if (l[0] == moveToPos) {
                moveToPos = l[1];
            }
        }

        for (int[] s : snakes) {
            if (s[0] == moveToPos) {
                moveToPos = s[1];
            }
        }
        int ones = moveToPos % 10;
        int tens = findTens(moveToPos);

        p.setPosition(moveToPos);
    }

    /***
     * Checks if the last square has been updated, if it has,
     * the player in that square has won.
     * @return A boolean value if someone has won or not.
     */
    public boolean won() {
        return board[9][9] != " ";
    }

    /***
     * Starts the game, one at the time, the player rolls the dice and moves
     * correspondingly and updates and prints the board after each turn. Until one of the players
     * reaches the last square, then the method stops and return the winner.
     * @return the player which won the game.
     */
    public Player run() {
        updateBoard();
        Player winner = null;
        Dice dice = new Dice();
        while (winner == null) {
            for (Player p : players) {
                int goAgainInt = 1;
                int diceRoll = dice.rollDice();

                movePlayer(diceRoll, p);

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    System.out.println(e);
                }
                System.out.println(p.getId() + " rolled " + diceRoll);
                updateBoard();
                printBoard();

                while (diceRoll == 6 && goAgainInt < 3) {
                    diceRoll = dice.rollDice();

                    movePlayer(diceRoll, p);
                    goAgainInt++;
                    if (goAgainInt == 3) {
                        movePlayer(1, p);
                    }


                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        System.out.println(e);
                    }
                    System.out.println(p.getId() + " rolled " + diceRoll);
                    updateBoard();
                    printBoard();
                    if (won() && p.getPosition() == 99) {
                        winner = p;
                        System.out.println("The winner is " + p.getId());
                        return p;
                    }
                }

                if (won() && p.getPosition() == 99) {
                    winner = p;
                    System.out.println("The winner is " + p.getId());
                    return p;
                }


            }

        }
        return null;
    }
}
