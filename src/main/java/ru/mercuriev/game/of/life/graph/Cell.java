package ru.mercuriev.game.of.life.graph;

import java.util.Objects;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static ru.mercuriev.game.of.life.graph.CellUtils.*;

/**
 * works only with cells,
 * do not know anything about arrays
 *
 * @author paul
 */
final class Cell {

    Integer currentState = 0; // 0 - dead, 1 - live. represents current generation
    Cell left = null;
    Cell top = null;
    Cell right = null;
    Cell bottom = null;

    // TODO in the aims of debugging create private String tag for the cell ?

    private Cell() {
    }

    /**
     * package level constructor for testing purpose only
     */
    // TODO remove ?
    Cell(int currentState) {
        this.currentState = currentState;
    }

    /**
     * @return Cell representation of Stream<Stream<Integer>>
     */
    static Cell valueOf(Stream<IntStream> input) {

        Objects.nonNull(input);

        return input.map(stream -> stream.<CellHolder>collect( // building CellHolder holding Cell representation of single Stream<Integer>
                                                             CellHolder::new,
                                                             (line, value) -> {
                                                                Cell next = new Cell(); // TODO remove this constructor
                                                                next.left = line.current;
                                                                line.current.currentState = value;
                                                                line.current.right = next;
                                                                line.current = next;
                                                             },
                                                             (left, right) -> {
                                                                left.current.right = right.current;
                                                                right.current.left = left.current;
                                                                left.current = left.current.right; // see IntPipeLine.collect
                                                             }))
                    .collect(CellHolder::new, // building CellHolder holding Cell representation of Stream<Stream<Integer>>
                             CellHolder::mergeLines,
                             CellHolder::mergeLines)
                    .toCell(); // getting Cell from CellHolder

    }

    /**
     * @return Stream representation of the Cell
     */
    public Stream<IntStream> toStream() {

        Cell current = this;
        Objects.nonNull(current);

        Stream.Builder<IntStream> columnBuilder =Stream.builder();

        do {

            IntStream intStream = lineToStream(current);
            columnBuilder.add(intStream);
            current = moveNextLine(current);

        } while (current != null);

        return columnBuilder.build();

    }



    /**
     * @return cell representing next generation
     */
    public Cell calculateNextGeneration() {

        // TODO implement

//        Cell current = this;
//
//        while (true) { // TODO bad bad bad rewrite
//
//
//            current.calculateNextState(this.getCountOfLiveNeighbours());
//            while (current.right != null) {
//                current = current.right;
//                current.calculateNextState(this.getCountOfLiveNeighbours());
//            }
//
//            TODO use moveNextLine
//            // rewind to the left most cell
//            while (current.left != null) // duplicate code
//                current = current.left; sdfsdfsdfsd //TODO move to the specific method
//
//            if (current.bottom != null)
//                current = current.bottom;
//            else {
//                break;
//            }
//
//        }

        return new Cell();

    }

}

