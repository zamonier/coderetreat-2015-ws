package ru.mercuriev.game.of.life.no.condition;

import ru.mercuriev.game.of.life.no.condition.cells.Cell;
import ru.mercuriev.game.of.life.no.condition.worlds.World;
import ru.mercuriev.game.of.life.no.condition.worlds.WrappedWorld;

import java.util.ArrayList;
import java.util.List;

public class NeighboursCollector {

    WrappedWorld wrappedWorld;

    public NeighboursCollector(World world) {
        this.wrappedWorld = WrappedWorld.newInstance(world);
    }

    public int neighboursAmount(Cell c) {
        return this.collectNeighbours(c)
                .stream()
                .mapToInt(Cell::getState)
                .sum();
    }

    List<Cell> collectNeighbours(Cell cell) {
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
}
