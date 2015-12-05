package ru.mercuriev.game.of.life.graph;

import java.util.Arrays;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * Engine interface implementation
 * Uses Cell uder the hood to calculate next generation
 *
 * @author paul
 */
// TODO implement Engine interface
public class GraphEngine {

    // TODO @Override
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
