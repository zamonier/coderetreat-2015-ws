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

    Integer currentState = -1; // 0 - dead, 1 - live. represents current generation
    Integer nextState = -1; // 0 - dead, 1 - live. represents next generation
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
     *
     */
    private Cell calculateNextGeneration() {

        Cell current = this;

        do {
            current.calculateNextState();

            while (current.right != null) {
                current.calculateNextState();
                current = current.right;
            }

            // rewind to the left most cell
            while (current.left != null)
                current = current.left;

        } while ();

        return this;

    }

    private void calculateNextState() {

    }

}

