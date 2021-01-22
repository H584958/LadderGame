package main;

import boardGame.Board;
/***
 * A Main to run the Board game.
 * @author Simon Kobbenes, Matias Vedeler, Christian Evensen
 */
public class Main {
    public static void main(String[] args) {
        Board board = new Board(4);
        board.run();
    }
}
