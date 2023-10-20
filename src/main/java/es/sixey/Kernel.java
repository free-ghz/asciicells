package es.sixey;

import java.util.Arrays;

public class Kernel {
    private final Cell[] cells;
    private final int diameter;
    public Kernel(Board board, int centerx, int centery, int diameter) {
        var arrayLength = diameter * diameter;
        this.cells = new Cell[arrayLength];
        var relativeCornerX = centerx - (diameter/2);
        var relativeCornerY = centery - (diameter/2);
        for (int i = 0; i < arrayLength; i++) {
            var relativeX = i % diameter;
            var relativeY = i / diameter;
            var boardX = relativeCornerX + relativeX;
            var boardY = relativeCornerY + relativeY;
            this.cells[i] = board.cellAt(boardX, boardY);
        }
        this.diameter = diameter;
    }

    public int getSum() {
        int sum = 0;
        for (var cell : cells) {
                sum += cell.getState();
        }
        return sum;
    }

    @Override
    public boolean equals(Object other) {
        if (other instanceof Kernel otherKernel) {
            if (otherKernel.diameter != this.diameter) return false;

            for (int i = 0; i < cells.length; i++) {
                if (cells[i].getState() != otherKernel.cells[i].getState()) {
                    return false;
                }
            }

            return true;
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Arrays.toString(cells).hashCode(); // cheating  a bit hehe ;3
    }
}
