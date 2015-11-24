package ru.mercuriev.game.of.life.graph;

import java.util.stream.IntStream;

/**
 * works only with cells,
 * do not know anything about arrays
 *
 * @author paul
 */
final class Cell {

    private Cell top = null;
    private Cell bottom = null;
    private Cell left = null;
    private Cell right = null;

    /**
     * initializes current generation of the world
     */
    static Cell newInstance(IntStream inputData, int lineLength) {
        // TODO do not pass lineLength - calculate it
        // 1. produce FIRST Cell linked by Cell1.right = Cell2.left with length "lineLength"
        // 2. produce NEXT Cell linked by Cell1.right = Cell2.left with length "lineLength"
        // 3. merge FIRST.bottom and NEXT.up cells,
        // 4. repeat (lineLength - 2) times
        return null;
    }

    /**
     * produces the next generation of World
     *
     * @return left top cell pointer
     */
    Cell nextGeneration() {
        // 1. for each cell in world line by line count neighbohoods and decide will it be alive
        // 2. put values it to the IntStream
        // 3. call newInstance
        return null;
    }

}

