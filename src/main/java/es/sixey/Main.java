package es.sixey;

import java.io.StringReader;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        int kernelSize = 3;
        int numberOfStates = 3;
        int boardWidth = 16;
        int boardHeight = 10;
        int gameRounds = 100;

        var ruleset = SumRuleset.generateArbitrary(numberOfStates, kernelSize);
        var initBoard = new Board(boardWidth, boardHeight, numberOfStates);
        var game = new Game(initBoard, ruleset, kernelSize);

        var alphabet = new Alphabet();
        for (int i = 0; i < gameRounds; i++) {
            System.out.println("\n");
            System.out.println(Pipes.pipes(game.getBoard()));
            game.step();
        }
    }
}