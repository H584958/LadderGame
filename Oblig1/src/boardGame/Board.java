package boardGame;

import java.util.List;

/***
 *
 */
public class Board {
    private int[][] ladders;
    private int[][] snakes;
    private String[][] board;
    private List<Player> players;
    private int numPlayers;

    public Board(int numPlayers) {
        board = new String[10][10];
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                board[i][j] = " ";
            }
        }
        String[] names = "WASD".split("");
        for (int i = 0; i < numPlayers; i++) {
            players.add(new Player(names[i]));
        }

        ladders = new int[][]{{1, 34}, {8, 12}, {28, 84}, {50, 72}};
        snakes = new int[][]{{80, 69}, {25, 13}, {43, 20}, {95, 85}};

    }

    public void printBoard() {
        int num = 1;
        for (String[] e : board) {
            if (num % 2 == 0) {
                for (int i = 9; i >= 0; i--) {
                    System.out.print("[" + ((e[i]) + "] "));
                }
            } else {
                for (int i = 0; i < 10; i++) {
                    System.out.print("[" + ((e[i]) + "] "));
                }
            }
            num++;
            System.out.println();
        }
    }

    public void updateBoard() {
        for (int [] l : ladders) {

        }
    }

    public int findTens ( int pos) {
        int tens = 0;
        if (pos % 10 == pos) {
            tens = 0;
        } else {
            tens = pos / 10;
        }
        return tens;
    }

    public void movePlayer(int diceRoll, Player p) {
        int moveToPos = diceRoll + p.getPosition();

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

    public boolean won() {
        return board[10][10] != " ";
    }

    public Player run() {
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

                    printBoard();
                }

                if (won()) {
                    winner = p;
                }


            }

        }
        return winner;
    }
}
