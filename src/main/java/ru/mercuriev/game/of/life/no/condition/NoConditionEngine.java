package ru.mercuriev.game.of.life.no.condition;

import org.springframework.stereotype.Service;
import ru.mercuriev.game.of.life.no.condition.cells.Cell;

import java.util.List;
import java.util.stream.Collectors;

import static ru.mercuriev.game.of.life.no.condition.DisionMaker.nextGenCell;

@Service
public class NoConditionEngine {

    public int[][] next(int[][] ints) {

        World world = World.newInstance(ints);
        WrappedWorld w = WrappedWorld.newInstance(world);
        NeighboursCollector neighboursCollector = new NeighboursCollector(w);

        List<List<Cell>> nextGeneration = world.getRows().map(row ->
                row.stream()
                        .map(cell -> nextGenCell(cell, neighboursCollector.sum(cell)))
                        .collect(Collectors.toList())
        ).collect(Collectors.toList());

        return World.newInstance(nextGeneration).toArray();
    }

}