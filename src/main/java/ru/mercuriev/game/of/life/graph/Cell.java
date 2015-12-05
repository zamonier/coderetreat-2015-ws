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
        return getCellHolder(getStreamOfLines(input)).toCell();
    }

    /**
     * building CellHolder for Cell representation of single IntStream - one line fo cells
     * @return Stream of CellHolder. Each CellHolder in stream represents a line of cells.
     */
    // TODO rename
    static Stream<CellHolder> getStreamOfLines(Stream<IntStream> input) {
        return input.map(stream -> stream.collect(CellHolder::new,
                                                  CellHolder::append,
                                                  CellHolder::mergeHorizontal));
    }

    /**
     * building CellHolder holding Cell representation of Stream<CellHolder> in a single CellHolder
     * @return CellHolder representing all lines of cell merged
     */
    // TODO rename
    static CellHolder getCellHolder(Stream<CellHolder> lines) {
        return lines.collect(CellHolder::new,
                             CellHolder::mergeVertical,
                             CellHolder::mergeVertical);
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
        return cellToStream(this,cell -> calculateNextState(cell, getCountOfNeighboursAlive(cell)));
    }

    /**
     * @return Cell bottom of most left cell in line. null if bottom of the  most left is null
     */
    static Cell getNextLineCell(Cell cell) {

        Cell current = cell;
        if (current == null) {
            return null;
        }

        // left rewind
        while (current.left != null)
            current = current.left;

        // move to the next cell in column
        return current.bottom;

    }

    /**
     * This method can be called ONLY for the most left cell in the line - no left rewind will be performed
     * @return IntStream of the results of function.apply(cell) applied to each cell in line
     */
    static IntStream lineToStream(Cell cell, Function<Cell,Integer> function) {

        Cell current = cell;
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
    static Stream<IntStream> cellToStream(Cell cell, Function<Cell,Integer> function) {

        Cell current = cell;
        if (current == null) {
            return Stream.empty();
        }

        Stream.Builder<IntStream> columnBuilder = Stream.builder();
        do {

            IntStream intStream = lineToStream(current,function);
            columnBuilder.add(intStream);
            current = getNextLineCell(current);

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
    static int getCountOfNeighboursAlive(Cell current) {

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
