package ru.mercuriev.game.of.life.graph;

import java.util.Arrays;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * interface Engine implementation and proxy to the Cells (convert int[][] to Cell)
 *
 * @author paul
 */
public class GraphEngine {

    /**
     * interface method implementation
     */
    // TODO move method to the interface
    public int[][] next(int[][] currentGeneration) {

        // int[][] -> Stream<IntStream>
        Stream<IntStream> input = Arrays.stream(currentGeneration)
                                        .map(Arrays::stream);

        Stream<IntStream> nextGeneration = Cell.valueOf(input).getNextGeneration();

        // Stream<IntStream> -> int[][]
        return nextGeneration.map(IntStream::toArray)
                             .toArray(int[][]::new);

    }

}
