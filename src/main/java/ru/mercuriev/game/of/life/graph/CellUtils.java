package ru.mercuriev.game.of.life.graph;

import java.util.Objects;
import java.util.stream.IntStream;

/**
 * TODO write description
 * @author paul
 */
public class CellUtils {

    //  TODO public enum CellState (DEAD, ALIVE)

    public static final int DEAD = 0;
    public static final int ALIVE = 1;

    /**
     * @return Cell bottom of most left cell in line. null if bottom of the  most left is null
     */
    static Cell moveNextLine(Cell current) {

        Objects.nonNull(current);

        // rewind to the left
        while (current.left != null)
            current = current.left;

        if (current.bottom != null)
            current = current.bottom;

        return current;

    }

    static IntStream lineToStream(Cell firstInline) {

        Cell current = firstInline;

        IntStream.Builder lineBuilder = IntStream.builder();

        // construction stream representation of Cell line
        lineBuilder.add(current.currentState);
        while (current.right != null) {
            current = current.right;
            lineBuilder.add(current.currentState);
        }
        return lineBuilder.build();

    }

    /**
     * @return state of the cell in the next generation
     */
    static int calculateNextState(Cell current, int countOfLiveNeighbours) {

        Objects.nonNull(current);
        if (current.currentState == DEAD && countOfLiveNeighbours == 3) {
            return ALIVE;
        }
        if (current.currentState == ALIVE && (countOfLiveNeighbours == 2 || countOfLiveNeighbours == 3)) {
            return ALIVE;
        }
        return DEAD;

    }

    /**
     * @return count of alive Neighbours of the cell
     */
    static int getCountOfLiveNeighbours(Cell current) {  // TODO. things planned to be simple look like shit. rewrite

        Objects.nonNull(current);
        int count = 0;

        if (current.top != null) {

            count += current.top.currentState;

            if (current.top.left != null)
                count += current.top.left.currentState;

            if (current.top.right != null)
                count += current.top.right.currentState;
        }

        if (current.bottom != null) {

            count += current.bottom.currentState;

            if (current.bottom.left != null)
                count += current.bottom.left.currentState;

            if (current.bottom.right != null)
                count += current.bottom.right.currentState;
        }

        if (current.left != null) {
            count += current.left.currentState;
        }

        if (current.right != null) {
            count += current.right.currentState;
        }

        return count;

    }

}
