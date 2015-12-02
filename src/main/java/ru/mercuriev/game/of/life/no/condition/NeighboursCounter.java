package ru.mercuriev.game.of.life.no.condition;

import ru.mercuriev.game.of.life.no.condition.cells.Cell;
import ru.mercuriev.game.of.life.no.condition.worlds.World;
import ru.mercuriev.game.of.life.no.condition.worlds.WrappedWorld;

import java.util.stream.Stream;

public class NeighboursCounter {

    private WrappedWorld world;

    public static NeighboursCounter forWorld(World world) {
        NeighboursCounter counter = new NeighboursCounter();
        counter.world = WrappedWorld.newInstance(world);
        return counter;
    }

    public int neighboursAmount(Cell cell) {
        return this.collectNeighbours(cell)
                .mapToInt(Cell::getState)
                .sum();
    }

    private Stream<Cell> collectNeighbours(Cell cell) {

        int i = cell.getX();
        int j = cell.getY();

        Stream.Builder<Cell> neighbours = Stream.builder();

        // add top neighbours
        neighbours.add(world.cellAt(i - 1, j - 1))
                .add(world.cellAt(i - 1, j))
                .add(world.cellAt(i - 1, j + 1));

        // add side neighbours
        neighbours.add(world.cellAt(i, j - 1))
                .add(world.cellAt(i, j + 1));

        // add bottom neighbours
        neighbours.add(world.cellAt(i + 1, j - 1))
                .add(world.cellAt(i + 1, j))
                .add(world.cellAt(i + 1, j + 1));

        return neighbours.build();
    }
}
