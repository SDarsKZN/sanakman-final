package logic;

public class Cell {
    private CellState state;
    private final int x;
    private final int y;

    public Cell(int x, int y, CellState state) {
        this.x = x;
        this.y = y;
        this.state = state;
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    public CellState getState() {
        return this.state;
    }

    public void setState(CellState state) {
        this.state = state;
    }
}
