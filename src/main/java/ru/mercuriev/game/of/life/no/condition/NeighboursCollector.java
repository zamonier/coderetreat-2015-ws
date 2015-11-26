package ru.mercuriev.game.of.life.no.condition;

import ru.mercuriev.game.of.life.no.condition.cells.Cell;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Eugene on 24.11.15.
 */
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
}
