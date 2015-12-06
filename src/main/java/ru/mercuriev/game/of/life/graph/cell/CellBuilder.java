package ru.mercuriev.game.of.life.graph.cell;

import java.util.Objects;
import java.util.Optional;

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
final class CellBuilder {

    /**
     * current cell
     */
    private Cell cell = null;

    public CellBuilder append(int value) {

        if (cell == null) {
            cell = new Cell(value);
        } else {
            Cell next = new Cell(value);
            cell.right = next;
            next.left = cell;
            cell = cell.right;
        }
        return this;

    }

    /**
     * merge this CellBuilder with another horizontally
     * as a result - the most right cell of this builder is merged with the most left of the right builder
     * @return this
     * @throws NullPointerException if rightBuilder == null
     */
    public CellBuilder mergeHorizontal(CellBuilder rightBuilder) {

        CellBuilder leftBuilder = this;
        Objects.requireNonNull(rightBuilder);

        if (rightBuilder.cell == null) {
            return this; // no merge is needed
        }

        if (leftBuilder.cell == null) {
            leftBuilder.cell = rightBuilder.cell;
            rightBuilder.cell = null;
            return this; // no merge is needed - only copy left to right
        }

        // go to the most left cell in right
        Cell current = rightBuilder.cell;
        while (current.left != null)
            current = current.left;

        // merge the end of the left and the beginning of the right
        leftBuilder.cell.right = current;
        current.left = leftBuilder.cell;

        leftBuilder.cell = rightBuilder.cell;
        rightBuilder.cell = null;
        return this;

    }

    /**
     * merge this CellBuilder with another vertically
     * as a result - the bottom line of graph in this builder is merged with the top line in bottomBuilder
     * @return this
     * @throws NullPointerException if bottomBuilder == null
     */
    @SuppressWarnings("SuspiciousNameCombination")
    public CellBuilder mergeVertical(CellBuilder bottomBuilder) {

        Objects.requireNonNull(bottomBuilder);
        CellBuilder topBuilder = this;

        if (topBuilder.cell == null && bottomBuilder.cell == null) {
            return bottomBuilder; // no merge is needed
        }

        if (topBuilder.cell == null) {
            topBuilder.cell = bottomBuilder.cell;
            return bottomBuilder; // no merge is needed - only copy bottom to top
        }

        if (bottomBuilder.cell == null) {
            bottomBuilder.cell = topBuilder.cell;
            return bottomBuilder; // no merge is needed - only copy top to bottom
        }

        // now current cells of both builders are at the right most position

        Cell topBuilderCurrent = topBuilder.cell;
        Cell bottomBuilderCurrent = bottomBuilder.cell;

        // rewind both lines to the left most cell
        while (topBuilderCurrent.left != null)
            topBuilderCurrent = topBuilderCurrent.left;
        while (bottomBuilderCurrent.left != null)
            bottomBuilderCurrent = bottomBuilderCurrent.left;

        // in order to merge topBuilder and bottomBuilder
        // 1. bottom line of the topBuilder
        // should be merged with
        // 2. top line of the bottomBuilder

        // making 1. true
        while (topBuilderCurrent.bottom != null)
            topBuilderCurrent = topBuilderCurrent.bottom; // TODo check

        // making 2. true
        while (bottomBuilderCurrent.top != null)
            bottomBuilderCurrent = bottomBuilderCurrent.top;

        // it is ok to start merging

        // glue the top and bottom cell in one column
        topBuilderCurrent.bottom = bottomBuilderCurrent;
        bottomBuilderCurrent.top = topBuilderCurrent;
        while (topBuilderCurrent.right != null && bottomBuilderCurrent.right != null) {

            // move to the next cell in line
            topBuilderCurrent = topBuilderCurrent.right;
            bottomBuilderCurrent = bottomBuilderCurrent.right;

            // glue the top and bottom cell in one column
            topBuilderCurrent.bottom = bottomBuilderCurrent;
            bottomBuilderCurrent.top = topBuilderCurrent;
        }

        this.cell = bottomBuilder.cell;

        return this;
    }

    /**
     * @return Optional of the left and top most cell in the graph
     */
    public Optional<Cell> build() {

        if (cell == null) {
            return Optional.empty();
        }

        Cell current = cell;
        while (current.left != null)
            current = current.left;
        while (current.top != null)
            current = current.top;
        return Optional.of(current);

    }

    /**
     * @return cell.toString() if cell is not null, or <[empty]>
     * @see Cell#toString()
     */
    @Override
    public String toString() {

        Cell current = cell;
        if (current != null) {
            return cell.toString();
        } else {
            return "<[empty]>";
        }

    }

}
