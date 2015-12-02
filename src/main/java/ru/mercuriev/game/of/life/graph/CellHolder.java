package ru.mercuriev.game.of.life.graph;

import java.util.Objects;

/**
 * This object holds the pointer to the cell while constructing new world of cells
 *
 * Each time any method of the object is called current cell is pointed to the most right bottom cell
 *
 * Class is not thread safe - do not use in parallel stream without proper synchronization
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

    @SuppressWarnings("SuspiciousNameCombination")
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
        // 1. cell of the top CellHolder should point to the left & bottom most cell in line.
        // this condition is true out of the box  - for the bucket of single line it is always true,
        // and for bucket of multiple lines mergeVertical returns CellHolder
        // with cell pointed to the last cell of the bottom bucket

        // 2. cell of the bottom CellHolder should point to the left & top most cell in line.
        // this condition is false - rewind top should be done
        while (bottomCurrent.top != null)
            bottomCurrent = bottomCurrent.top;

        // it is ok to start merging

        // glue the top and bottom cell in one column
        topCurrent.bottom = bottomCurrent;
        bottomCurrent.top = topCurrent;
        while (topCurrent.right != null && bottomCurrent.right != null)  {

            // move to the next cell in line
            topCurrent = topCurrent.right;
            bottomCurrent = bottomCurrent.right;

            // glue the top and bottom cell in one column
            topCurrent.bottom = bottomCurrent;
            bottomCurrent.top = topCurrent;
        }

        return bottom;
    }

    public Cell toCell() {

        Cell current = cell;

        if (current != null) {
            while (current.left != null)
                current = current.left;

            while (current.top != null)
                current = current.top;

            return current;
        }

        return null;

    }

    @Override
    // TODO implement
    public String toString() {

        Cell current = cell;
        if (current != null) {

            while (current.left != null)
                current = current.left;

//            while (current.top != null)
//                current = current.top;

            StringBuilder builder = new StringBuilder();
            builder.append(current.state);
            while (current.right != null) {
                current = current.right;
                builder.append(current.state);
            }
            return builder.toString();

//            return current.toStream()
//                          .map(ints -> ints.mapToObj(value -> "" + value)
//                                           .collect(Collectors.joining(",", "[", "]"))
//                              )
//                          .collect(Collectors.joining("\n", "<", ">"));
        } else {
            return "[empty]";
        }

    }

}
