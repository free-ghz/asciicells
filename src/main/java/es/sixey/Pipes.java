package es.sixey;

import java.util.List;
import java.util.Map;

import static java.util.Map.entry;

public class Pipes {
    private final static int HAS_UP = 1;
    private final static int HAS_RIGHT = 2;
    private final static int HAS_DOWN = 4;
    private final static int HAS_LEFT = 8;
    private final static int IS_BIG = 16;
    private final static int IS_SMALL = 32;
    private final static Map<Integer, String> PIPES = Map.ofEntries(
            entry(0, " "),

            entry(IS_SMALL + HAS_LEFT, "─"),
            entry(IS_SMALL + HAS_RIGHT, "─"),
            entry(IS_SMALL + HAS_LEFT + HAS_RIGHT, "─"),
            entry(IS_SMALL + HAS_LEFT + HAS_RIGHT + HAS_UP, "┴"),
            entry(IS_SMALL + HAS_LEFT + HAS_RIGHT + HAS_DOWN, "┬"),

            entry(IS_SMALL + HAS_UP, "│"),
            entry(IS_SMALL + HAS_DOWN, "│"),
            entry(IS_SMALL + HAS_UP + HAS_DOWN, "│"),
            entry(IS_SMALL + HAS_UP + HAS_DOWN + HAS_LEFT, "┤"),
            entry(IS_SMALL + HAS_UP + HAS_DOWN + HAS_RIGHT, "├"),

            entry(IS_SMALL + HAS_UP + HAS_LEFT, "┘"),
            entry(IS_SMALL + HAS_UP + HAS_RIGHT, "└"),
            entry(IS_SMALL + HAS_DOWN + HAS_LEFT, "┐"),
            entry(IS_SMALL + HAS_DOWN + HAS_RIGHT, "┌"),

            entry(IS_SMALL + HAS_UP + HAS_DOWN + HAS_LEFT + HAS_RIGHT, "┼"),
            entry(IS_SMALL, "o"),

            entry(IS_BIG + HAS_LEFT, "═"),
            entry(IS_BIG + HAS_RIGHT, "═"),
            entry(IS_BIG + HAS_LEFT + HAS_RIGHT, "═"),
            entry(IS_BIG + HAS_LEFT + HAS_RIGHT + HAS_UP, "╩"),
            entry(IS_BIG + HAS_LEFT + HAS_RIGHT + HAS_DOWN, "╦"),

            entry(IS_BIG + HAS_UP, "║"),
            entry(IS_BIG + HAS_DOWN, "║"),
            entry(IS_BIG + HAS_UP + HAS_DOWN, "║"),
            entry(IS_BIG + HAS_UP + HAS_DOWN + HAS_LEFT, "╣"),
            entry(IS_BIG + HAS_UP + HAS_DOWN + HAS_RIGHT, "╠"),

            entry(IS_BIG + HAS_UP + HAS_LEFT, "╝"),
            entry(IS_BIG + HAS_UP + HAS_RIGHT, "╚"),
            entry(IS_BIG + HAS_DOWN + HAS_LEFT, "╗"),
            entry(IS_BIG + HAS_DOWN + HAS_RIGHT, "╔"),

            entry(IS_BIG + HAS_UP + HAS_DOWN + HAS_LEFT + HAS_RIGHT, "╬"),
            entry(IS_BIG, "0")
    );

    public static String pipes(Board board) {
        StringBuilder out = new StringBuilder();
        for (int y = 0; y < board.getHeight(); y++) {
            for (int x = 0; x < board.getWidth(); x++) {
                var kernel = new Kernel(board, x, y, 3);
                var flags = 0;
                if (kernel.cellAt(1, 0).getState() > 0) flags += HAS_UP;
                if (kernel.cellAt(1, 2).getState() > 0) flags += HAS_DOWN;
                if (kernel.cellAt(0, 1).getState() > 0) flags += HAS_LEFT;
                if (kernel.cellAt(2, 1).getState() > 0) flags += HAS_RIGHT;
                if (kernel.cellAt(1, 1).getState() == 2) flags += IS_BIG;
                if (kernel.cellAt(1, 1).getState() == 1) flags += IS_SMALL;

                var symbol = PIPES.get(flags);
                if (symbol == null) {
                    symbol = PIPES.get(0);
                }

                out.append(symbol);
            }
            out.append("\n");
        }

        return out.toString();
    }

    private static void throwie(int flags) {
        System.err.println("CANT DEAL WITH FLAGS " + flags);
        List.of(HAS_UP,
                HAS_RIGHT,
                HAS_DOWN,
                HAS_LEFT,
                IS_BIG,
                IS_SMALL).forEach(flag -> {
                    if ((flag & flags) > 0) {
                        System.out.print(flag + " ");
                    }
        });
        throw new RuntimeException("j");
    }
}
