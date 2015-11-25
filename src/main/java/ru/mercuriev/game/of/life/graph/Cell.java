package ru.mercuriev.game.of.life.graph;

import java.util.Arrays;
import java.util.Objects;

/**
 * works only with cells,
 * do not know anything about arrays
 *
 * @author paul
 */
final class Cell {

    public static final int DEAD = 0;
    public static final int ALIVE = 1;

    Integer currentState = 0; // 0 - dead, 1 - live. represents current generation
    Integer nextState = 0; // 0 - dead, 1 - live. represents next generation
    Cell left = null;
    Cell top = null;
    Cell right = null;
    Cell bottom = null;

    // TODO in the aims of debugging create private String tag for the cell ?

    /**
     * initializes current generation of the world
     */
    static Cell newInstance(Integer[][] inputData) {
        // TODO put here Stream<Stream<Integer>>

        Objects.nonNull(inputData);

        return Arrays.stream(inputData)
                .map(integers -> Arrays.stream(integers)
                        .collect(new LineCollector()))
                .collect(CellHolder::new,
                        CellHolder::mergeLines,
                        CellHolder::mergeLines)
                .toCell()
                .calculateNextGeneration();

    }

    /**
     * calculate the next generation of World
     */
    private Cell calculateNextGeneration() {

        Cell current = this;

        while (true) { // TODO bad bad bad rewrite

            // calculate next state for all cells in line
            current.calculateNextState();
            while (current.right != null) {
                current = current.right;
                current.calculateNextState();
            }

            // rewind to the left most cell
            while (current.left != null)
                current = current.left;

            if (current.bottom != null)
                current = current.bottom;
            else {
                break;
            }

        }

        return this;

    }

    private void calculateNextState() { // TODO bad. rewrite

        int count = getCount();
        if (this.currentState == ALIVE && (count == 2 || count == 3)) {
            this.nextState = ALIVE;
            return;
        }
        if (this.currentState == DEAD && count == 3) {
            this.nextState = ALIVE;
            return;
        }
        this.nextState = DEAD;

    }

    private int getCount() {  // TODO. things planned to be simple look like shit. rewrite

        int count = 0;

        if (this.top != null) {

            count += this.top.currentState;

            if (this.top.left != null)
                count += this.top.left.currentState;

            if (this.top.right != null)
                count += this.top.right.currentState;
        }

        if (this.bottom != null) {

            count += this.bottom.currentState;

            if (this.bottom.left != null)
                count += this.bottom.left.currentState;

            if (this.bottom.right != null)
                count += this.bottom.right.currentState;
        }

        if (this.left != null) {
            count += this.left.currentState;
        }

        if (this.right != null) {
            count += this.right.currentState;
        }

        return count;

    }

}

