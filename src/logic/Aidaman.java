package logic;

public class Aidaman {
    private int x;
    private int y;
    private int direction;

    public Aidaman(int x, int y) {
        this.x = x;
        this.y = y;
        this.direction = 0;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getDirection() {
        return direction;
    }

    public void setDirection(int direction) {
        this.direction = direction;
    }
}
