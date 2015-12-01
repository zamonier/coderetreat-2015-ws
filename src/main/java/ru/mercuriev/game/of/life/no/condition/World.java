package ru.mercuriev.game.of.life.no.condition;

import ru.mercuriev.game.of.life.no.condition.cells.AliveCell;
import ru.mercuriev.game.of.life.no.condition.cells.BorderCell;
import ru.mercuriev.game.of.life.no.condition.cells.Cell;
import ru.mercuriev.game.of.life.no.condition.cells.DeadCell;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * Representation of board(world) with cells on it
 *
 * @author Eugene
 * @since 26.11.15.
 */
public final class World {

    public static final int BORDER_OFFSET = 1;

    private List<List<Cell>> cells;

    private World(int size) {
        this.cells = new ArrayList<>(size + 2 * BORDER_OFFSET);
    }

    public static World newInstance(int[][] world) {
        World build = new WorldBuilder(world).build();
        return new WrappedWorldBuilder(build).build();
    }

    public List<Cell> getRow(int i) {
        List<Cell> row = this.cells.get(i + BORDER_OFFSET);
        return row.subList(BORDER_OFFSET, row.size() - BORDER_OFFSET);
    }

    public int size() {
        return cells.size() - 2 * BORDER_OFFSET;
    }

    public Cell cellAt(int i, int j) {
        return cells.get(i + BORDER_OFFSET).get(j + BORDER_OFFSET);
    }

    private static class WrappedWorldBuilder {

        private int size;
        private World w;

        public WrappedWorldBuilder(World world) {
            this.size = world.cells.size();
            this.w = world;
        }

        protected World build() {

            List<List<Cell>> result = new ArrayList<>();

            result.add(borderRow());
            IntStream.range(0, w.cells.size()).forEach(i -> result.add(wrappedRow(i)));
            result.add(borderRow());
            w.cells = result;
            return w;
        }

        private List<Cell> wrappedRow(int i) {
            return Stream.concat(
                    Stream.concat(
                            Stream.of(new BorderCell()),
                            w.cells.get(i).stream()
                    ),
                    Stream.of(new BorderCell())
            ).collect(Collectors.toList());
        }

        private List<Cell> borderRow() {
            return Stream.generate(BorderCell::new).limit(size + 2 * BORDER_OFFSET).collect(Collectors.toList());
        }

    }

    public static class WorldBuilder {
        private static Class<Cell>[] prototypes = new Class[]{DeadCell.class, AliveCell.class};
        private int[][] world;
        private int size;
        private World w;

        public WorldBuilder(int[][] world) {
            this.size = world.length;
            w = new World(size);
            this.world = world;
        }

        protected World build() {

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
