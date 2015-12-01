package ru.mercuriev.game.of.life.no.condition;

import ru.mercuriev.game.of.life.no.condition.cells.Cell;

import java.util.ArrayList;
import java.util.List;

public class NeighboursCollector {

    WrappedWorld wrappedWorld;

    public NeighboursCollector(WrappedWorld wrappedWorld) {
        this.wrappedWorld = wrappedWorld;
    }

    public List<Cell> collect(Cell cell) {
        List<Cell> n = new ArrayList<>();

        int i = cell.getX();
        int j = cell.getY();

        n.add(wrappedWorld.cellAt(i - 1, j - 1));
        n.add(wrappedWorld.cellAt(i - 1, j));
        n.add(wrappedWorld.cellAt(i - 1, j + 1));

        n.add(wrappedWorld.cellAt(i, j - 1));
        n.add(wrappedWorld.cellAt(i, j + 1));

        n.add(wrappedWorld.cellAt(i + 1, j - 1));
        n.add(wrappedWorld.cellAt(i + 1, j));
        n.add(wrappedWorld.cellAt(i + 1, j + 1));

        return n;
    }

    public int sum(Cell c) {
        return this.collect(c)
                .stream()
                .mapToInt(Cell::getState)
                .sum();
    }
}
