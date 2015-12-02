package ru.mercuriev.game.of.life.no.condition;

import org.springframework.stereotype.Service;
import ru.mercuriev.game.of.life.no.condition.worlds.World;

import java.util.stream.Collectors;

import static ru.mercuriev.game.of.life.no.condition.DisionMaker.nextGenCell;

@Service
public class NoConditionEngine {

    public int[][] next(int[][] ints) {

        World world = World.fromArray(ints);

        NeighboursCollector neighboursCollector = new NeighboursCollector(world);

        World nextGenWorld = World.fromList(
                world.rowsAsStream().map(
                        row -> row.stream()
                                .map(cell -> nextGenCell(cell, neighboursCollector.neighboursAmount(cell)))
                                .collect(Collectors.toList())
                ).collect(Collectors.toList())
        );

        return nextGenWorld.toArray();
    }


}