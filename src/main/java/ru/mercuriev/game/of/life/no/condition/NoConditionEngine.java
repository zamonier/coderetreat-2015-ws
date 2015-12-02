package ru.mercuriev.game.of.life.no.condition;

import org.springframework.stereotype.Service;
import ru.mercuriev.game.of.life.no.condition.cells.Cell;
import ru.mercuriev.game.of.life.no.condition.worlds.World;

import static ru.mercuriev.game.of.life.no.condition.DisionMaker.nextGenCell;

@Service
public class NoConditionEngine {

    public int[][] next(int[][] ints) {

        World world = World.fromArray(ints);

        NeighboursCollector neighboursCollector = new NeighboursCollector(world);

        return world.rowsAsStream().map(
                row -> row.stream()
                        .map(cell -> nextGenCell(cell, neighboursCollector.neighboursAmount(cell)))
                        .mapToInt(Cell::getState)
                        .toArray()
        ).toArray(int[][]::new);
    }


}