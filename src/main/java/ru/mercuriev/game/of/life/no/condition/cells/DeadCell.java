package ru.mercuriev.game.of.life.no.condition.cells;

import ru.mercuriev.game.of.life.Engine;

public class DeadCell extends Cell {

    @Override
    public int getState() {
        return Engine.DEAD;
    }
}
