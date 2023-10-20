package es.sixey;

public class Cell {
    private int state = 0;

    public Cell(){}
    public Cell(int state) {
        this.state = state;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }
}
