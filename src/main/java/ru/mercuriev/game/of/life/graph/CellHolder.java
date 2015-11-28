package ru.mercuriev.game.of.life.graph;

/**
 * TODO write appropriate description
 * @author paul
 */
class CellHolder {

    Cell current = null; // TODO Make private. write set & get

    public static CellHolder mergeLines(CellHolder top, CellHolder bottom) {

        // rewind both lines to the left most cell
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
