package ru.mercuriev.game.of.life.graph;

import java.util.Objects;

/**
 * This object holds the pointer to the cell while constructing new world of cells
 *
 * Each time any method of the object is called current cell is pointed to the most right bottom cell
 *
 * Class is nor thread safe - do not use in parallel stream without proper synchronization
 *
 * @author paul
 */
class CellHolder {

    Cell cell = null; // TODO Make private. write set & get

    public static void append(CellHolder line, int value) {
        Objects.nonNull(line);
        if (line.cell == null) {
            line.cell = new Cell(value);
        } else {
            Cell next = new Cell(value);
            line.cell.right = next;
            next.left = line.cell;
            line.cell = line.cell.right;
        }
    }

    public static void mergeHorizontal(CellHolder left, CellHolder right) {

        Objects.nonNull(left);
        Objects.nonNull(right);

        if (left.cell == null && right.cell == null) {
            return; // no merge is needed
        }

        if (right.cell == null) {
            right.cell = left.cell;
            return; // no merge is needed - only copy right to left
        }

        if (left.cell == null) {
            left.cell = right.cell;
            return; // no merge is needed - only copy left to right
        }

        // go to the most left cell in right cellHolder
        Cell current = right.cell;
        while (current.left != null)
            current = current.left;

        // merge the end of the left and the beginning of the right
        left.cell.right = current;
        current.left = left.cell;

        // make left cellHolder current cell point ot the most right cell in line
        // because cell lines in both cellHolder are now the same
        left.cell = right.cell; // see IntPipeLine.collect
    }

    public static CellHolder mergeVertical(CellHolder top, CellHolder bottom) {

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

        // rewind both lines to the left most cell
        while (top.cell.left != null)
            top.cell = top.cell.left;
        while (bottom.cell.left != null)
            bottom.cell = bottom.cell.left;

        while (top.cell.right != null && bottom.cell.right != null) {

            // glue the top and bottom cell in one column
            top.cell.bottom = bottom.cell;
            bottom.cell.top = top.cell;

            // move to the next cell in line
            top.cell = top.cell.right;
            bottom.cell = bottom.cell.right;

        }

        return bottom;
    }

    public Cell toCell() {

        while (cell.left != null)
            cell = cell.left;

        while (cell.top != null)
            cell = cell.top;

        return cell;

    }

    @Override
    public String toString() {

        Cell current = cell;

        if (current != null) {

            while (current.left != null)
                current = current.left;

            String result = "" + current.state;
            while (current.right != null) {
                current = current.right;
                result += "" + current.state;
            }
            return result;

        } else {

            return "[empty]";

        }

    }

}
