package ru.mercuriev.game.of.life.graph;

import java.util.Objects;
import java.util.function.Function;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * works only with cells,
 * do not know anything about arrays
 *
 * @author paul
 */
final class Cell {

    private static final int DEAD = 0;
    private static final int ALIVE = 1;

    Integer currentState = 0;
    Cell left = null;
    Cell top = null;
    Cell right = null;
    Cell bottom = null;

    // TODO in the aims of debugging create private String tag for the cell ?

    Cell() {
    }

    /**
     * @return Cell representation of Stream<Stream<Integer>>
     */
    static Cell valueOf(Stream<IntStream> input) {

        Objects.nonNull(input);

        return input.map(stream -> stream.collect( // building CellHolder holding Cell representation of single IntStream
                                          CellHolder::new,
                                          CellHolder::append,
                                          CellHolder::mergeHorizontal))
                    .collect(CellHolder::new, // building CellHolder holding Cell representation of Stream<Stream<Integer>>
                             CellHolder::mergeVertical,
                             CellHolder::mergeVertical)
                    .toCell(); // getting Cell from CellHolder

    }

    /**
     * @return Stream representation of the Cell
     */
    public Stream<IntStream> toStream() {
        return cellToStream(this,cell -> cell.currentState);
    }

    /**
     * @return Stream representing next generation
     */
    public Stream<IntStream> getNextGeneration() {
        return cellToStream(this,cell -> calculateNextState(cell,getCountOfLiveNeighbours(cell)));
    }

    /**
     * @return Cell bottom of most left cell in line. null if bottom of the  most left is null
     */
    static Cell moveNextLine(Cell current) {

        Objects.nonNull(current);

        // rewind to the left
        while (current.left != null)
            current = current.left;

        if (current.bottom != null)
            current = current.bottom;

        return current;

    }

    /**
     * @return IntStream of the results of function.apply(cell) applied to each cell in line
     */
    static IntStream lineToStream(Cell firstInline, Function<Cell,Integer> function) {

        Cell current = firstInline;

        IntStream.Builder lineBuilder = IntStream.builder();

        lineBuilder.add(function.apply(current));
        while (current.right != null) {
            current = current.right;
            lineBuilder.add(function.apply(current));
        }
        return lineBuilder.build();

    }

    /**
     * @return  Stream<IntStream> of the results of function.apply(cell) applied to each cell
     */
    static Stream<IntStream> cellToStream(Cell current, Function<Cell,Integer> function) {

        Objects.nonNull(current);
        Stream.Builder<IntStream> columnBuilder =Stream.builder();
        do {

            IntStream intStream = lineToStream(current,function);
            columnBuilder.add(intStream);
            current = moveNextLine(current);

        } while (current != null);
        return columnBuilder.build();

    }

    /**
     * @return state of the cell in the next generation
     */
    static int calculateNextState(Cell current, int countOfLiveNeighbours) {

        Objects.nonNull(current);
        if (current.currentState == DEAD && countOfLiveNeighbours == 3) {
            return ALIVE;
        }
        if (current.currentState == ALIVE && (countOfLiveNeighbours == 2 || countOfLiveNeighbours == 3)) {
            return ALIVE;
        }
        return DEAD;

    }

    /**
     * @return count of alive Neighbours of the cell
     */
    static int getCountOfLiveNeighbours(Cell current) {

        Objects.nonNull(current);
        int count = 0;

        if (current.top != null) {
            count += current.top.currentState;
            if (current.top.left != null)
                count += current.top.left.currentState;
            if (current.top.right != null)
                count += current.top.right.currentState;
        }

        if (current.bottom != null) {
            count += current.bottom.currentState;
            if (current.bottom.left != null)
                count += current.bottom.left.currentState;
            if (current.bottom.right != null)
                count += current.bottom.right.currentState;
        }

        if (current.left != null) {
            count += current.left.currentState;
        }

        if (current.right != null) {
            count += current.right.currentState;
        }

        return count;

    }

}

