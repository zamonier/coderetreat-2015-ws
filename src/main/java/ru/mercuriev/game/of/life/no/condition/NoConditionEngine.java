package ru.mercuriev.game.of.life.no.condition;

import org.springframework.stereotype.Service;
import ru.mercuriev.game.of.life.no.condition.worlds.World;

import java.util.stream.Collectors;

import static ru.mercuriev.game.of.life.no.condition.DisionMaker.nextGenCell;

@Service
public class NoConditionEngine {

    public int[][] next(int[][] world) {
        World world1 = World.newInstance(world);

        NeighboursCollector neighboursCollector = new NeighboursCollector(world1);

        World nextGenWorld = World.fromList(
                world1.getRows().map(row ->
                        row.stream()
                                .map(cell -> nextGenCell(cell, neighboursCollector.sum(cell)))
                                .collect(Collectors.toList())
                ).collect(Collectors.toList())
        );

        return nextGenWorld.toArray();
    }


}