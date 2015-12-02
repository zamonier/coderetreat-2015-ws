package ru.mercuriev.game.of.life.no.condition.cells;

public class AliveCell extends Cell {

    @Override
    public int getState() {
        return Cell.ALIVE;
    }
}
