package ru.mercuriev.game.of.life.no.condition;

import ru.mercuriev.game.of.life.no.condition.cells.Cell;

import java.util.ArrayList;
import java.util.List;

public class NeighboursCollector {

    World world;

    public NeighboursCollector(World world) {
        this.world = world;
    }

    public List<Cell> collect(Cell cell) {
        List<Cell> n = new ArrayList<>();

        int i = cell.getX();
        int j = cell.getY();

        n.add(world.cellAt(i - 1, j - 1));
        n.add(world.cellAt(i - 1, j));
        n.add(world.cellAt(i - 1, j + 1));

        n.add(world.cellAt(i, j - 1));
        n.add(world.cellAt(i, j + 1));

        n.add(world.cellAt(i + 1, j - 1));
        n.add(world.cellAt(i + 1, j));
        n.add(world.cellAt(i + 1, j + 1));

        return n;
    }

    public int sum(Cell c) {
        return this.collect(c)
                .stream()
                .mapToInt(Cell::getState)
                .sum();
    }
}
