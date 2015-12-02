package ru.mercuriev.game.of.life.no.condition.worlds;

import ru.mercuriev.game.of.life.no.condition.cells.Cell;

import java.util.ArrayList;
import java.util.List;

/**
 * Representation board(world) with of OutOfRangeError protection
 *
 * @author Eugene
 * @since 26.11.15.
 */
public final class WrappedWorld {

    List<List<Cell>> cells = new ArrayList<>();

    public static final int BORDER_OFFSET = 1;

    public static WrappedWorld newInstance(World world) {
        return new WrappedWorldBuilder(world).build();
    }

    public Cell cellAt(int i, int j) {
        return cells.get(i + BORDER_OFFSET).get(j + BORDER_OFFSET);
    }

}
