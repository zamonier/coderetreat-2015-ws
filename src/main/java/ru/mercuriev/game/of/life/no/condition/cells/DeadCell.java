package ru.mercuriev.game.of.life.no.condition.cells;

public class DeadCell extends Cell {

    @Override
    public int getState() {
        return DEAD;
    }
}
