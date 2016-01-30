package ru.mercuriev.game.of.life.graph;

import ru.mercuriev.game.of.life.graph.cell.Cell;

import java.util.Arrays;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * Engine interface implementation
 *
 * int arrays are used only to pass the current generation in {@link GraphEngine#next(int[][])} and return the next
 * generation data from it.
 * Calculation of the next generation is based on using {@link Cell} objects and {@link Stream} objects to pass
 * the data in and return the data out.
 *
 * So this is no-arrays-using implementation of the Game of Life
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
