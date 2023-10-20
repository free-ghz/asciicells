package es.sixey;

import java.io.StringReader;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        int kernelSize = 3;
        int numberOfStates = 3;
        int boardWidth = 80;
        int boardHeight = 40;
        int gameRounds = 100;

        var ruleset = PositionalRuleset.generateArbitrary(numberOfStates, kernelSize);
        // var initBoard = new Board(boardWidth, boardHeight, (x, y) -> {
        //     if (y > 2 && y < boardHeight - 3) {
        //         if ((x+4) % 8 == 0) return 2;
        //     }
        //     return 0;
        // });
        var initBoard = new Board(boardWidth, boardHeight, (x, y) -> {
            if (((x+(y*boardWidth)) % 31) == 0) return 1;
            return 0;
        });
        //var initBoard = new Board(boardWidth, boardHeight, (x, y) -> {
        //    if (((x+(y*boardWidth)) % 711) == 0) return 1;
        //    if (((x+(y*boardWidth)) % 911) == 0) return 2;
        //    return 0;
        //});
        //var initBoard = new Board(boardWidth, boardHeight, (x, y) -> {
        //    if (x == 38 || x == 41) {
        //        if (y >= 18 && y <= 21) {
        //            return 1;
        //        }
        //    }
        //    if (x > 38 && x < 41) {
        //        if (y == 18 || y == 21) {
        //            return 1;
        //        }
        //    }
        //    return 0;
        //});
        var game = new Game(initBoard, ruleset, kernelSize);

        while(true) {
            System.out.print(Pipes.pipes(game.getBoard()));
            Thread.sleep(100);
            game.step();
        }
    }
}