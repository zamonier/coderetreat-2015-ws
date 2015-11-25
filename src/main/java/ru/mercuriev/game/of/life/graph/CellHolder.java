package ru.mercuriev.game.of.life.graph;

/**
 * @author paul
 */
class CellHolder { // TODO rename - this is not generation? but line of cells

    Cell current = null;

    public static CellHolder mergeLines(CellHolder top, CellHolder bottom) {

        while (top.current.left != null)
            top.current = top.current.left;

        while (bottom.current.left != null)
            bottom.current = bottom.current.left;

        while (top.current.right != null && bottom.current.right != null) {

            // glue the top and bottom cell in one column
            top.current.bottom = bottom.current;
            bottom.current.top = top.current;

            // move to the next cell in line
            top.current = top.current.right;
            bottom.current = bottom.current.right;

        }

        return bottom;
    }

    public Cell toCell() {

        while (this.current.left != null)
            this.current = this.current.left;

        while (this.current.top != null)
            this.current = this.current.top;

        return current;


    }

}
