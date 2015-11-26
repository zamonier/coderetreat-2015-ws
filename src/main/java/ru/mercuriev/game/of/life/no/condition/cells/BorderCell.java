package ru.mercuriev.game.of.life.no.condition.cells;

/**
 * Created by Eugene on 26.11.15.
 */
public class BorderCell extends Cell {
    public BorderCell() {
        super(-1, 1);
    }

    @Override
    public int getState() {
        return DEAD;
    }
}