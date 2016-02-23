package ru.mercuriev.game.of.life.graph.cell;

import java.util.Arrays;
import java.util.Objects;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static org.testng.Assert.assertTrue;

/**
 * Utility class for testing Cell & CellBuilder
 *
 * @author paul
 */
public class CellTestUtils {

    static int[][] CURRENT_GENERATION = {
            {0, 0, 0, 0, 0},
            {0, 0, 0, 1, 0},
            {0, 1, 0, 1, 0},
            {0, 0, 1, 1, 0},
            {0, 0, 0, 0, 0},
    };
    static Stream<IntStream> CURRENT_GENERATION_STREAM = Arrays.stream(CURRENT_GENERATION).map(Arrays::stream);

    static int[][] NEXT_GENERATION = {
            {0, 0, 0, 0, 0},
            {0, 0, 1, 0, 0},
            {0, 0, 0, 1, 1},
            {0, 0, 1, 1, 0},
            {0, 0, 0, 0, 0},
    };
    static Stream<IntStream> NEXT_GENERATION_STREAM = Arrays.stream(NEXT_GENERATION).map(Arrays::stream);

    static CellBuilder constructLine(int... values) {
        CellBuilder cellBuilder = new CellBuilder();
        for (int value : values) {
            cellBuilder.append(value);
        }
        return cellBuilder;
    }

    static Cell constructMultiline(int[][] values) {

        int lineCounter = 0;

        Cell prev = null;
        while (lineCounter < values.length) {

            CellBuilder cellBuilder = constructLine(values[lineCounter]);
            Cell next = cellBuilder.build().orElse(null);

            // merging only first cells in lines - result is not the canonical cell, but it is ok for test
            if (prev != null && next != null) {
                next.top = prev;
                prev.bottom = next;
            }
            prev = next;

            lineCounter++;
        }

        // rewind to the top most cell
        if (prev != null) {
            while (prev.top != null) {
                prev = prev.top;
            }
        }

        return prev;

    }

    @SuppressWarnings("SuspiciousNameCombination")
    static void checkLinesAreMerged(Cell top, Cell bottom) {

        Objects.nonNull(top);
        Objects.nonNull(bottom);

        Cell topCurrent = top;
        Cell bottomCurrent = bottom;

        // rewind to the left most cell
        while (topCurrent.left != null)
            topCurrent = topCurrent.left;
        while (bottomCurrent.left != null)
            bottomCurrent = bottomCurrent.left;

        while (topCurrent.right != null && bottomCurrent.right != null) {
            assertTrue(topCurrent.bottom == bottomCurrent);
            assertTrue(bottomCurrent.top == topCurrent);
            topCurrent = topCurrent.right;
            bottomCurrent = bottomCurrent.right;
        }

    }

}
