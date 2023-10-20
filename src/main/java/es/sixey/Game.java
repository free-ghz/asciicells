package es.sixey;

public class Game {
    private Board current;
    private Board next;
    private final Ruleset ruleset;
    private final int kernelDiameter;

    public Game(Board init, Ruleset ruleset, int kernelDiameter) {
        current = init;
        this.ruleset = ruleset;
        this.kernelDiameter = kernelDiameter;
    }

    public void step() {
        next = new Board(current.getWidth(), current.getHeight());
        for (int x = 0; x < current.getWidth(); x++) {
            for (int y = 0; y < current.getWidth(); y++) {
                var kernel = new Kernel(current, x, y, kernelDiameter);
                var newState = ruleset.lookup(kernel);
                next.cellAt(x, y).setState(newState);
            }
        }
        current = next;
    }
    public Board getBoard() {
        return current;
    }

    @Override
    public String toString() {
        StringBuilder out = new StringBuilder();
        for (int y = 0; y < current.getHeight(); y++) {
            for (int x = 0; x < current.getWidth(); x++) {
                out.append(current.cellAt(x, y).getState());
            }
            out.append("\n");
        }
        return out.toString();
    }
}
