package ru.mercuriev.game.of.life.graph.cell;

import java.util.Objects;

/**
 * This object
 * 1. holds the pointer to the current cell while constructing new graph
 * 2. appends new cells to the graph
 * 3. merging together current and specified CellBuilder
 * Each time any method of the object is called current cell - right and bottom most cell in graph
 * <p>
 * Class is not thread safe - do not use it without proper synchronization (e.g. in parallel stream )
 *
 * @author paul
 */
// todo move all comments to javadoc
final class CellBuilder {

    /**
     * current cell
     */
    private Cell cell = null;

    public void append(int value) {

        if (cell == null) {
            cell = new Cell(value);
        } else {
            Cell next = new Cell(value);
            cell.right = next;
            next.left = cell;
            cell = cell.right;
        }

    }

    /**
     * merge this CellBuilder with another horizontally
     * as a result - the most right cell of this CellBuilder is merged with the most left of the right CellBuilder
     */
    public void mergeHorizontal(CellBuilder right) {

        CellBuilder left = this;
        Objects.nonNull(right);

        if (left.cell == null && right.cell == null) {
            return; // no merge is needed
        }

        if (right.cell == null) {
            right.cell = left.cell; // TODO remove
            return; // no merge is needed - only copy right to left
        }

        if (left.cell == null) {
            left.cell = right.cell;
            return; // no merge is needed - only copy left to right
        }

        // go to the most left cell in right
        Cell current = right.cell;
        while (current.left != null)
            current = current.left;

        // merge the end of the left and the beginning of the right
        left.cell.right = current;
        current.left = left.cell;

        // make left builder's current cell point ot the most right cell in line
        // because cell lines in both builders are now the same
        left.cell = right.cell; // see IntPipeLine.collect
    }

    //  TODO public CellBuilder mergeHorizontal(CellBuilder bottom);

    @SuppressWarnings("SuspiciousNameCombination")
    public static CellBuilder mergeVertical(CellBuilder top, CellBuilder bottom) {

        Objects.nonNull(top);
        Objects.nonNull(bottom);

        if (top.cell == null && bottom.cell == null) {
            return bottom; // no merge is needed
        }

        if (top.cell == null) {
            top.cell = bottom.cell;
            return bottom; // no merge is needed - only copy bottom to top
        }

        if (bottom.cell == null) {
            bottom.cell = top.cell;
            return bottom; // no merge is needed - only copy top to bottom
        }

        // now current cells of both line buckets are at the right most position

        Cell topCurrent = top.cell;
        Cell bottomCurrent = bottom.cell;

        // rewind both lines to the left most cell
        while (topCurrent.left != null)
            topCurrent = topCurrent.left;
        while (bottomCurrent.left != null)
            bottomCurrent = bottomCurrent.left;

        // in order to merge top line bucket and bottom line bucket
        // 1. bottom line of the top line bucket
        // should be merged with
        // 2. top line of the bottom line bucket

        // for merging from left to right current
        //
        // 1. cell of the top CellBuilder should point to the left & bottom most cell in line.
        // this condition is true out of the box  - for the bucket of single line it is always true,
        // and for bucket of multiple lines mergeVertical returns CellBuilder
        // with cell pointed to the last cell of the bottom bucket
        // TODO WTF?! it is not true? WHY?
        // TODO need to do this for proper working
        while (topCurrent.bottom != null)
            topCurrent = topCurrent.bottom;

        // 2. cell of the bottom CellBuilder should point to the left & top most cell in line.
        // this condition is false - rewind top should be done
        while (bottomCurrent.top != null)
            bottomCurrent = bottomCurrent.top;

        // it is ok to start merging

        // glue the top and bottom cell in one column
        topCurrent.bottom = bottomCurrent;
        bottomCurrent.top = topCurrent;
        while (topCurrent.right != null && bottomCurrent.right != null) {

            // move to the next cell in line
            topCurrent = topCurrent.right;
            bottomCurrent = bottomCurrent.right;

            // glue the top and bottom cell in one column
            topCurrent.bottom = bottomCurrent;
            bottomCurrent.top = topCurrent;
        }

        return bottom;
    }

    /**
     * @return the left and top most cell in the graph
     */
    // TODO return optional
    public Cell build() {

        if (cell == null) {
            return null;
        }

        Cell current = cell;
        while (current.left != null)
            current = current.left;
        while (current.top != null)
            current = current.top;
        return current;

    }

    /**
     * @deprecated use cell.toString()
     */
    @Override
    @Deprecated
    public String toString() {

        Cell current = cell;
        if (current != null) {
            return cell.toString();
        } else {
            return "<[empty]>";
        }

    }

}
