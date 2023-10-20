package es.sixey;

import java.util.Random;

public class Board {
    private final int width;
    private final int height;
    private final Cell[] cells;

    public Board(int width, int height) {
        this.width = width;
        this.height = height;
        var arrayLength = width*height;
        cells = new Cell[arrayLength];
        for (int i = 0; i < arrayLength; i++) {
            cells[i] = new Cell();
        }
    }

    public Board(int width, int height, Decorator decorator) {
        this.width = width;
        this.height = height;
        var arrayLength = width*height;
        cells = new Cell[arrayLength];
        Random random = new Random();
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                var location = (j*width) + i;
                cells[location] = new Cell(decorator.stateAt(i, j));
            }
        }
    }

    public Board(int width, int height, int maxState) {
        this(width, height, (x, y) -> new Random().nextInt(maxState));
    }

    public Board(int width, int height, Cell[] cells) {
        this.width = width;
        this.height = height;
        this.cells = cells;
    }

    public Cell cellAt(int x, int y) {
        while (x < 0) {
            x = width - x;
        }
        while (y < 0) {
            y = height - y;
        }
        while (x >= width) {
            x = x - width;
        }
        while (y >= height) {
            y = y - height;
        }

        var location = (y*width) + x;
        return cells[location];
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public interface Decorator {
        int stateAt(int x, int y);
    }
}
