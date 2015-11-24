package ru.mercuriev.game.of.life.no.condition;

/**
 * Created by Eugene on 24.11.15.
 */
public abstract class Cell {
    public static final int ALIVE = 1;
    public static final int DEAD = 0;
    private int x;
    private int y;
    private int state;

    public Cell(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public abstract int getState();
}
