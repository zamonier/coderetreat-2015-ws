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

    static final int DEAD = 0;
    static final int ALIVE = 1;

    int state = 0;
    Cell left = null;
    Cell top = null;
    Cell right = null;
    Cell bottom = null;

    Cell() {
    }

    Cell(int state) {
        this.state = state;
    }

    /**
     * @return Cell representation of Stream<Stream<Integer>>
     */
    // TODO check stream is ordered
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
        return cellToStream(this,cell -> cell.state);
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
        if (current == null) {
            return IntStream.empty();
        }

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

        Objects.nonNull(current); // todo return empty
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
        if (current.state == DEAD && countOfLiveNeighbours == 3) {
            return ALIVE;
        }
        if (current.state == ALIVE && (countOfLiveNeighbours == 2 || countOfLiveNeighbours == 3)) {
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
            count += current.top.state;
            if (current.top.left != null)
                count += current.top.left.state;
            if (current.top.right != null)
                count += current.top.right.state;
        }

        if (current.bottom != null) {
            count += current.bottom.state;
            if (current.bottom.left != null)
                count += current.bottom.left.state;
            if (current.bottom.right != null)
                count += current.bottom.right.state;
        }

        if (current.left != null) {
            count += current.left.state;
        }

        if (current.right != null) {
            count += current.right.state;
        }

        return count;

    }

}

