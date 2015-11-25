package ru.mercuriev.game.of.life.graph;

import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;

/**
 * @author paul
 */
public class LineCollector implements Collector<Integer, CellHolder, CellHolder> {

    @Override
    public Supplier<CellHolder> supplier() {
        return CellHolder::new;
    }

    @Override
    public BiConsumer<CellHolder, Integer> accumulator() {
        return (line, integer) -> {
            Cell next = new Cell();
            next.left = line.current;
            line.current.currentState = integer;
            line.current.right = next;
            line.current = next;
        };
    }

    @Override
    public BinaryOperator<CellHolder> combiner() {

        return (left, right) -> {
            left.current.right = right.current;
            right.current.left = left.current;
            return right;
        };


    }

    @Override
    public Function<CellHolder, CellHolder> finisher() {
        return cellHolder -> {
            // rewind to the most left
            while (cellHolder.current.left != null) {
                cellHolder.current = cellHolder.current.left;
            }
            return cellHolder;

        };
    }

    @Override
    public Set<Characteristics> characteristics() {
        return null;
    }

}

