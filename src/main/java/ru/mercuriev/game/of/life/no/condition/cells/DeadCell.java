package ru.mercuriev.game.of.life.no.condition.cells;

/**
 * Created by Eugene on 26.11.15.
 */
public class DeadCell extends Cell {
    public DeadCell(int x, int y) {
        super(x, y);
    }

    @Override
    public int getState() {
        return DEAD;
    }
}
