package ru.mercuriev.game.of.life.no.condition;

import org.springframework.stereotype.Service;
import ru.mercuriev.game.of.life.no.condition.cells.Cell;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static ru.mercuriev.game.of.life.no.condition.DisionMaker.nextGenCell;

@Service
public class NoConditionEngine {

    public int[][] next(int[][] ints) {

        World world = World.newInstance(ints);
        WrappedWorld w = WrappedWorld.newInstance(world);
        NeighboursCollector neighboursCollector = new NeighboursCollector(w);

        final List<List<Cell>> result = new ArrayList<>();

        world.getRows().forEach(row -> {
            List<Cell> nextGen = row.stream()
                    .map(cell -> nextGenCell(cell, neighboursCollector.sum(cell)))
                    .collect(Collectors.toList());
            result.add(nextGen);
        });

        World nextGen = World.newInstance(result);
        return nextGen.toArray();
    }

}