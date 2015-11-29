package ru.mercuriev.game.of.life.no.condition;

import org.springframework.stereotype.Service;
import ru.mercuriev.game.of.life.no.condition.cells.Cell;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
public class NoConditionEngine {

    public static final int ALIVE = 1;
    public static final int DEAD = 0;

    public int[][] next(int[][] world) {

        int size = world.length;
        int[][] result = new int[size][size];

        World w = World.newInstance(world);

        final List<List<Cell>> result2 = new ArrayList<>();

        IntStream.range(0, size).forEach(i -> {
            List<Cell> nextGen = w.getRow(i).stream()
                    .map(c -> {
                                int neighbours = new NeighboursCollector(w).collect(c)
                                        .stream()
                                        .mapToInt(Cell::getState)
                                        .sum();
                                return DisionMaker.nextGenCell(c, neighbours);
                            }
                    )
                    .collect(Collectors.toList());
            result2.add(nextGen);
        });

        IntStream.range(0, size).forEach(i ->
                IntStream.range(0, size).forEach(j ->
                        result[i][j] = result2.get(i).get(j).getState()
                )
        );

        return result;
    }


}