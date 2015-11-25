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
public class GenerationCollector implements Collector<CellHolder, CellHolder, Cell> {

    public static CellHolder mergeLines(CellHolder top, CellHolder bottom) {

        while (top.current.left != null)
            top.current = top.current.left;

        while (bottom.current.left != null)
            bottom.current = bottom.current.left;

        while (top.current.right != null && bottom.current.right != null) {

            // glue the top and bottom cell in one column
            top.current.bottom = bottom.current;
            bottom.current.top = top.current;

            // move to the next cell in line
            top.current = top.current.right;
            bottom.current = bottom.current.right;

        }

        return bottom;
    }

    @Override
    public Supplier<CellHolder> supplier() {
        return CellHolder::new;
    }

    @Override
    public BiConsumer<CellHolder, CellHolder> accumulator() {
        return GenerationCollector::mergeLines;
    }

    @Override
    public BinaryOperator<CellHolder> combiner() {
        return GenerationCollector::mergeLines;
    }

    @Override
    public Function<CellHolder, Cell> finisher() {
        return cellHolder -> cellHolder.current;
    }

    @Override
    public Set<Characteristics> characteristics() {
        return null;
    }
}
