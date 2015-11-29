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

    private World(int size) {
        this.cells = new ArrayList<>(size + 2);
    }

    public List<Cell> getRow(int i) {
        List<Cell> row = this.cells.get(i + 1);
        return row.subList(1, row.size() - 1);
    }

    private static class WorldBuilder {

        private int[][] world;
        private int size;
        private World w;

        private static Class<Cell>[] prototypes = new Class[]{DeadCell.class, AliveCell.class};

        public WorldBuilder(int[][] world) {
            this.world = world;
            this.size = world.length;
            w = new World(size);
        }

        private World build() {

            addBorderRow();
            IntStream.range(0, size).forEach(i -> {
                addWrappedRow(i);
            });
            addBorderRow();

            return w;
        }

        private void addWrappedRow(int i) {
            List<Cell> row = new ArrayList<>();
            row.add(new BorderCell());
            IntStream.range(0, size).forEach(j -> {
                        int state = world[i][j];
                        try {
                            Cell c = prototypes[state].newInstance();
                            c.setX(i);
                            c.setY(j);
                            row.add(c);
                        } catch (InstantiationException | IllegalAccessException e) {
                            e.printStackTrace();
                        }
                    }
            );
            row.add(new BorderCell());
            w.cells.add(row);
        }

        private void addBorderRow() {
            List<Cell> row = new ArrayList<>(size + 2);
            IntStream.range(0, size + 2).forEach(i1 -> {
                row.add(new BorderCell());
            });
            w.cells.add(row);
        }

    }

    public int size() {
        return cells.size() - 2 * BORDER_OFFSET;
    }

    public Cell cellAt(int i, int j) {
        return cells.get(i + BORDER_OFFSET).get(j + BORDER_OFFSET);
    }
}
