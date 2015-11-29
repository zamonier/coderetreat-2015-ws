package ru.mercuriev.game.of.life.no.condition.cells;

public class DeadCell extends Cell {

    public DeadCell() {
    }

    @Override
    public int getState() {
        return DEAD;
    }
}
