package ru.mercuriev.game.of.life.no.condition.cells;

public class BorderCell extends Cell {

    @Override
    public int getState() {
        return DEAD;
    }
}
