package ru.mercuriev.game.of.life.no.condition;

import ru.mercuriev.game.of.life.no.condition.cells.AliveCell;
import ru.mercuriev.game.of.life.no.condition.cells.Cell;
import ru.mercuriev.game.of.life.no.condition.cells.DeadCell;

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

    private List<List<Cell>> cells;

    protected World(int size) {
        this.cells = new ArrayList<>(size);
    }

    public Stream<List<Cell>> getRows() {
        return cells.stream();
    }

    public int size() {
        return cells.size();
    }

    public static World newInstance(int[][] world) {
        return new WorldBuilder(world).build();
    }

    public static World newInstance(List<List<Cell>> cells) {
        World world = new World(cells.size());
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

    public static class WorldBuilder {
        private static Class<Cell>[] prototypes = new Class[]{DeadCell.class, AliveCell.class};
        private int[][] world;
        private int size;

        public WorldBuilder(int[][] world) {
            this.size = world.length;
            this.world = world;
        }

        protected World build() {
            World w = new World(size);
            IntStream.range(0, size).forEach(i -> w.cells.add(row(i)));
            return w;
        }

        private List<Cell> row(int i) {
            List<Cell> row = new ArrayList<>();
            IntStream.range(0, size).forEach(j -> {
                row.add(newCell(i, j));
            });
            return row;
        }

        private Cell newCell(int i, int j) {
            try {
                int state = world[i][j];
                Cell c = prototypes[state].newInstance();
                c.setX(i);
                c.setY(j);
                return c;
            } catch (InstantiationException | IllegalAccessException e) {
                e.printStackTrace();
            }
            return null;
        }
    }
}
