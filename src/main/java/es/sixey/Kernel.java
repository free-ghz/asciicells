package es.sixey;

public class Kernel {
    private Board internalBoard;
    public Kernel(Board board, int centerx, int centery, int diameter) {
        var arrayLength = diameter * diameter;
        var cells = new Cell[arrayLength];
        var relativeCornerX = centerx - (diameter/2);
        var relativeCornerY = centery - (diameter/2);
        for (int i = 0; i < arrayLength; i++) {
            var relativeX = i % diameter;
            var relativeY = i / diameter;
            var boardX = relativeCornerX + relativeX;
            var boardY = relativeCornerY + relativeY;
            cells[i] = board.cellAt(boardX, boardY);
        }
        internalBoard = new Board(diameter, diameter, cells);
    }
}
