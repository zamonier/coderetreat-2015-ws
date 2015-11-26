package ru.mercuriev.game.of.life.no.condition;

import ru.mercuriev.game.of.life.no.condition.cells.AliveCell;
import ru.mercuriev.game.of.life.no.condition.cells.BorderCell;
import ru.mercuriev.game.of.life.no.condition.cells.Cell;
import ru.mercuriev.game.of.life.no.condition.cells.DeadCell;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

/**
 * Representation of board(world) with cells on it
 *
 * @author Eugene
 * @since 26.11.15.
 */
public final class World {

    public static final int BORDER_OFFSET = 1;

    private List<List<Cell>> cells;

    public static World newInstance(int[][] world) {
        return new WorldBuilder(world).build();
    }

    private World() {
    }

    private static class WorldBuilder {

        private int[][] world;
        private int size;

        public WorldBuilder(int[][] world) {
            this.world = world;
            this.size = world.length;
        }

        private World build() {

            World w = new World();

            w.cells = new ArrayList<>(size + 2);
            w.cells.add(borderRow());
            IntStream.range(0, size).forEach(i -> {
                w.cells.add(wrappedRow(world[i], i));
            });
            w.cells.add(borderRow());

            return w;
        }

        private List<Cell> wrappedRow(int[] ints, int i) {
            List<Cell> row = new ArrayList<>();
            row.add(new BorderCell());
            IntStream.range(0, size).forEach(j ->
                    row.add(ints[j] == 1 ? new AliveCell(i, j) : new DeadCell(i, j))
            );
            row.add(new BorderCell());
            return row;
        }

        private List<Cell> borderRow() {
            List<Cell> row = new ArrayList<>(size + 2);
            IntStream.range(0, size + 2).forEach(i -> {
                row.add(new BorderCell());
            });
            return row;
        }
    }

    public int size() {
        return cells.size() - 2 * BORDER_OFFSET;
    }

    public Cell cellAt(int i, int j) {
        return cells.get(i + BORDER_OFFSET).get(j + BORDER_OFFSET);
    }
}
