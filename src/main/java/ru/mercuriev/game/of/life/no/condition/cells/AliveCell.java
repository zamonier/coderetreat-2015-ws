package ru.mercuriev.game.of.life.no.condition.cells;

import ru.mercuriev.game.of.life.Engine;

public class AliveCell extends Cell {

    @Override
    public int getState() {
        return Engine.ALIVE;
    }
}
