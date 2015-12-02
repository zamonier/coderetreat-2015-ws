package ru.mercuriev.game.of.life.no.condition.cells;

public abstract class Cell {

    protected static final int ALIVE = 1;
    protected static final int DEAD = 0;

    private int x;
    private int y;

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public abstract int getState();
}
