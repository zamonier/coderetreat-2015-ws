package ru.mercuriev.game.of.life.no.condition;

import org.springframework.stereotype.Service;
import ru.mercuriev.game.of.life.no.condition.cells.Cell;
import ru.mercuriev.game.of.life.no.condition.worlds.World;

import static ru.mercuriev.game.of.life.no.condition.DecisionMaker.nextGenerationCell;

@Service
public class NoConditionEngine {

    public int[][] next(int[][] cells) {

        World world = World.fromArray(cells);
        NeighboursCounter neighboursCounter = NeighboursCounter.forWorld(world);

        return world.rowsAsStream()
                .map(row ->
                        row.stream()
                                .map(cell -> nextGenerationCell(cell, neighboursCounter.neighboursAmount(cell)))
                                .mapToInt(Cell::getState)
                                .toArray())
                .toArray(int[][]::new);
    }

}