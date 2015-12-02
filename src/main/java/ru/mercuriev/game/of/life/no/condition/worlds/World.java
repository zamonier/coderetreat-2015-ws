package ru.mercuriev.game.of.life.no.condition.worlds;

import ru.mercuriev.game.of.life.no.condition.cells.Cell;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * Representation of board(world) with cells on it
 *
 * @author Eugene
 * @since 26.11.15.
 */
public class World {

    List<List<Cell>> cells = new ArrayList<>();

    public Stream<List<Cell>> getRows() {
        return cells.stream();
    }

    public int size() {
        return cells.size();
    }

    public static World newInstance(int[][] world) {
        return new WorldBuilder(world).build();
    }

    public static World fromList(List<List<Cell>> cells) {
        World world = new World();
        world.cells = cells;
        return world;
    }

    public int[][] toArray() {
        int size = cells.size();
        int[][] result = new int[size][size];
        IntStream.range(0, size).forEach(i ->
                result[i] = cells.get(i).stream().mapToInt(Cell::getState).toArray()
        );
        return result;
    }

}
