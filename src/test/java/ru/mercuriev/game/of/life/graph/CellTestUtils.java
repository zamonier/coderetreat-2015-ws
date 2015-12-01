package ru.mercuriev.game.of.life.graph;

/**
 * Utility class for testing Cell & CellHolder
 *
 * @author paul
 */
public class CellTestUtils {

    static CellHolder constructLine(int ... values) {
        CellHolder cellHolder = new CellHolder();
        for (int value : values) {
            CellHolder.append(cellHolder,value);
        }
        return cellHolder;
    }

    static Cell constructCell(int[][] values) {

        int lineCounter = 0;

        Cell prev = null;
        while (lineCounter < values.length) {

            CellHolder cellHolder = constructLine(values[lineCounter]);
            Cell next = cellHolder.cell;

            // rewind to the left most cell in line
            if (next != null ) {
                while (next.left != null){
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

}
