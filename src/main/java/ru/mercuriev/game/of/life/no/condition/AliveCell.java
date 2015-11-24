package ru.mercuriev.game.of.life.no.condition;

/**
 * Created by Eugene on 24.11.15.
 */
public class AliveCell extends Cell {

    public AliveCell(int x, int y) {
        super(x, y);
    }

    @Override
    public int getState() {
        return Cell.ALIVE;
    }
}
