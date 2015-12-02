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

        private int size;
        private World ww;

        public WrappedWorldBuilder(World ww) {
            this.size = ww.cells.size();
            this.ww = ww;
        }

        protected WrappedWorld build() {

            WrappedWorld w = new WrappedWorld(size + 2 * BORDER_OFFSET);

            List<List<Cell>> result = new ArrayList<>();
            result.add(borderRow());
            result.addAll(ww.cells.stream().map(this::wrappedRow).collect(Collectors.toList()));
            result.add(borderRow());

            w.cells = result;
            return w;
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
            return Stream.generate(BorderCell::new).limit(size + 2 * BORDER_OFFSET).collect(Collectors.toList());
        }

    }

}
