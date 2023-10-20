package es.sixey;

import java.io.StringReader;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        int kernelSize = 3;
        int numberOfStates = 3;
        int boardWidth = 17;
        int boardHeight = 10;
        int gameRounds = 100;

        var ruleset = SumRuleset.generateArbitrary(numberOfStates, kernelSize);
        var initBoard = new Board(boardWidth, boardHeight, (x, y) -> {
            if (y > 2 && y < boardHeight - 3) {
                if ((x+4) % 8 == 0) return 2;
            }
            return 0;
        });
        var game = new Game(initBoard, ruleset, kernelSize);

        var alphabet = new Alphabet();
        for (int i = 0; i < gameRounds; i++) {
            System.out.println("\n");
            System.out.println(Pipes.pipes(game.getBoard()));
            Thread.sleep(333);
            game.step();
        }
    }
}