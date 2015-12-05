package ru.mercuriev.game.of.life.graph;

import java.util.Arrays;
import java.util.Objects;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static org.testng.Assert.assertTrue;

/**
 * Utility class for testing Cell & CellHolder
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

    // TODO Change signature to CellHolder constructLine(int value, int... values) - there can not be CellHolder with no Cell
    // TODO replace for CellHolder.append()
    /**
     * @deprecated use CellHolder.append()
     */
    @Deprecated
    static CellHolder constructLine(int... values) {
        CellHolder cellHolder = new CellHolder();
        for (int value : values) {
            CellHolder.append(cellHolder, value);
        }
        return cellHolder;
    }

    static Cell constructMultiline(int[][] values) {

        int lineCounter = 0;

        Cell prev = null;
        while (lineCounter < values.length) {

            CellHolder cellHolder = constructLine(values[lineCounter]);
            Cell next = cellHolder.cell;

            // rewind to the left most cell in line
            if (next != null) {
                while (next.left != null) {
                    next = next.left;
                }
            }

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

    /**
     * method execution will fall with error if
     * - top or bottom is null
     * - top or bottom lines have different "length"
     * - one of the bottom link cell in top cell line is not equals to the corresponding bottom cell
     * - one of the top link cell in bottom cell line is not equals to the corresponding top cell
     *
     */
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
