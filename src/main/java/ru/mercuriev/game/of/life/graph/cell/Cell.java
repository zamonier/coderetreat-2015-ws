package ru.mercuriev.game.of.life.graph.cell;

import ru.mercuriev.game.of.life.Engine;

import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * This class represents Cell. Cell is recursive data structure
 * Single Cell contains links to left, top, right and bottom neighbours
 * Set of linked Cell represents Graph
 *
 * All public methods of Cell should be called on Cell instance representing left and top most cell in the Graph
 * {@link CellBuilder#build()} result represent such Cell
 *
 * Cell is immutable after constructed (Cell.valueOf call)
 *
 * @author paul
 */
final public class Cell {

    static final int DEAD = Engine.DEAD;
    static final int ALIVE = Engine.ALIVE;

    int state = DEAD;
    Cell left = null;
    Cell top = null;
    Cell right = null;
    Cell bottom = null;

    Cell(int state) {
        this.state = state;
    }

    /**
     * @return Graph representation of Stream<Stream<Integer>>
     * @throws IllegalArgumentException if input stream, is empty; NullPointerException if input == null
     */
    public static Cell valueOf(Stream<IntStream> input) {
        Objects.requireNonNull(input);
        return buildGraph(buildLines(input)).build()
                                            .orElseGet(() -> {throw new IllegalArgumentException("Stream is empty");});
    }

    /**
     * @return Stream of CellBuilder. Each CellBuilder in stream represents a line graph of cells.
     */
    static Stream<CellBuilder> buildLines(Stream<IntStream> input) {
        return input.map(stream -> stream.collect(CellBuilder::new,
                                                  CellBuilder::append,
                                                  CellBuilder::mergeHorizontal)); // see IntPipeLine.collect
    }

    /**
     * @return CellBuilder representing full graph of cells
     */
    static CellBuilder buildGraph(Stream<CellBuilder> lines) {
        return lines.collect(CellBuilder::new,
                             CellBuilder::mergeVertical,
                             CellBuilder::mergeVertical);
    }

    /**
     * @return Stream representing next generation
     */
    public Stream<IntStream> getNextGeneration() {
        return cellToStream(cell -> cell.calculateNextState(cell.getCountOfNeighboursAlive()));
    }

    /**
     * @return bottom of most left cell in line. null if bottom of the  most left is null
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
    Stream<IntStream> cellToStream(Function<Cell,Integer> function) {

        Cell current = this;

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
    int calculateNextState(int countOfLiveNeighbours) {

        if (this.state == DEAD && countOfLiveNeighbours == 3) {
            return ALIVE;
        }
        if (this.state == ALIVE && (countOfLiveNeighbours == 2 || countOfLiveNeighbours == 3)) {
            return ALIVE;
        }
        return DEAD;

    }

    /**
     * @return count of alive Neighbours of the cell
     */
    int getCountOfNeighboursAlive() {

        int count = 0;

        if (top != null) {
            count += top.state;
            if (top.left != null)
                count += top.left.state;
            if (top.right != null)
                count += top.right.state;
        }

        if (bottom != null) {
            count += bottom.state;
            if (bottom.left != null)
                count += bottom.left.state;
            if (bottom.right != null)
                count += bottom.right.state;
        }

        if (left != null) {
            count += left.state;
        }

        if (right != null) {
            count += right.state;
        }

        return count;

    }

    /**
     * states of the one line are separated with spaces (' ') and are surrounded with '[' and ']'
     * lines are separated with '-' and are surrounded with '<' and '>'
     * example: <[0 1 0]-[0 0 0]-[1 1 1]>
     * @return string representation of the Graph
     */
    @Override
    public String toString() {

        Cell current = this;

        while (current.left != null)
            current = current.left;
        while (current.top != null)
            current = current.top;

        Stream<IntStream> stream = current.cellToStream(cell -> cell.state);

        return stream.map(intStream -> intStream.mapToObj(value -> "" + value)
                                                .collect(Collectors.joining(" ", "[", "]")))
                     .collect(Collectors.joining("-", "<", ">"));

    }

}

