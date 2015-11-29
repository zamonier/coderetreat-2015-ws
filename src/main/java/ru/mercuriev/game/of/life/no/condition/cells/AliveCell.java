package ru.mercuriev.game.of.life.no.condition.cells;

public class AliveCell extends Cell {

    public AliveCell() {
    }

    @Override
    public int getState() {
        return Cell.ALIVE;
    }
}
