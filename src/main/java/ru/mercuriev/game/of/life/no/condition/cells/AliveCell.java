package ru.mercuriev.game.of.life.no.condition.cells;

public class AliveCell extends Cell {

    public AliveCell() {
    }

    public AliveCell(int x, int y) {
        super(x, y);
    }

    @Override
    public int getState() {
        return Cell.ALIVE;
    }
}
