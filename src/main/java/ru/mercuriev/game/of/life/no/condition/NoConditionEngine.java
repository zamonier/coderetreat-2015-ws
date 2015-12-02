package ru.mercuriev.game.of.life.no.condition;

import org.springframework.stereotype.Service;
import ru.mercuriev.game.of.life.no.condition.cells.Cell;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
public class NoConditionEngine {

    public int[][] next(int[][] world) {

        World build = World.newInstance(world);
        WrappedWorld w = WrappedWorld.newInstance(build);
        NeighboursCollector collector = new NeighboursCollector(w);

        final List<List<Cell>> result = new ArrayList<>();

        IntStream.range(0, build.size()).forEach(i -> {
            List<Cell> nextGen = w.getRow(i).stream()
                    .map(c -> DisionMaker.nextGenCell(c, collector.sum(c)))
                    .collect(Collectors.toList());
            result.add(nextGen);
        });

        return toArray(result);
    }

    private static int[][] toArray(List<List<Cell>> cells) {
        int size = cells.size();
        int[][] result = new int[size][size];
        IntStream.range(0, size).forEach(i ->
                result[i] = cells.get(i).stream().mapToInt(Cell::getState).toArray()
        );
        return result;
    }

}