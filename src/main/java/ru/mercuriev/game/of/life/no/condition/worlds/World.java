package ru.mercuriev.game.of.life.no.condition.worlds;

import ru.mercuriev.game.of.life.no.condition.cells.Cell;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

/**
 * Representation of board(world) with cells on it
 *
 * @author Eugene
 * @since 26.11.15.
 */
public class World {

    List<List<Cell>> cells = new ArrayList<>();

    public static World fromArray(int[][] world) {
        return new WorldBuilder(world).build();
    }

    public Stream<List<Cell>> rowsAsStream() {
        return cells.stream();
    }

}
