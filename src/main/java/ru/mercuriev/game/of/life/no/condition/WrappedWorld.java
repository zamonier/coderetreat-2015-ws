package ru.mercuriev.game.of.life.no.condition;

import ru.mercuriev.game.of.life.no.condition.cells.BorderCell;
import ru.mercuriev.game.of.life.no.condition.cells.Cell;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Representation of board(world) with cells on it
 *
 * @author Eugene
 * @since 26.11.15.
 */
public final class WrappedWorld extends World {

    public static final int BORDER_OFFSET = 1;

    protected WrappedWorld(int size) {
        super(size);
        this.cells = new ArrayList<>(size + 2 * BORDER_OFFSET);
    }

    public static WrappedWorld newInstance(World world) {
        return new WrappedWorldBuilder(world).build();
    }

    public Cell cellAt(int i, int j) {
        return cells.get(i + BORDER_OFFSET).get(j + BORDER_OFFSET);
    }

    private static class WrappedWorldBuilder {

        private World world;
        private WrappedWorld wrappedWorld;

        public WrappedWorldBuilder(World world) {
            this.world = world;
            wrappedWorld = new WrappedWorld(world.size());
        }

        protected WrappedWorld build() {

            wrappedWorld.cells.add(borderRow());
            wrappedWorld.cells.addAll(world.cells.stream().map(this::wrappedRow).collect(Collectors.toList()));
            wrappedWorld.cells.add(borderRow());

            return wrappedWorld;
        }

        private List<Cell> wrappedRow(List<Cell> row) {
            return Stream.concat(
                    Stream.concat(
                            Stream.of(new BorderCell()),
                            row.stream()
                    ),
                    Stream.of(new BorderCell())).collect(Collectors.toList());
        }

        private List<Cell> borderRow() {
            return Stream.generate(BorderCell::new).limit(world.size() + 2 * BORDER_OFFSET).collect(Collectors.toList());
        }

    }

}
