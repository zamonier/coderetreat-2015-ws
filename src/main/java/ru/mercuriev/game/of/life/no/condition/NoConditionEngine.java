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

        int size = world.length;

        World w = World.newInstance(world);
        NeighboursCollector collector = new NeighboursCollector(w);

        final List<List<Cell>> cells = new ArrayList<>();

        IntStream.range(0, size).forEach(i -> {
            List<Cell> nextGen = w.getRow(i).stream()
                    .map(c -> {
                        int neighbours = collector.collect(c)
                                        .stream()
                                        .mapToInt(Cell::getState)
                                        .sum();
                                return DisionMaker.nextGenCell(c, neighbours);
                            }
                    )
                    .collect(Collectors.toList());
            cells.add(nextGen);
        });

        return toArray(cells);
    }

    private int[][] toArray(List<List<Cell>> cells) {
        int size = cells.size();
        int[][] result = new int[size][size];
        IntStream.range(0, size).forEach(i ->
                result[i] = cells.get(i).stream().mapToInt(Cell::getState).toArray()
        );
        return result;
    }

}