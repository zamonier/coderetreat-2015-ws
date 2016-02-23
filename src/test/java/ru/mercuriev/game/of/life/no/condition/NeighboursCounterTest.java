package ru.mercuriev.game.of.life.no.condition;

import org.junit.Test;
import ru.mercuriev.Fixture;
import ru.mercuriev.game.of.life.no.condition.cells.Cell;
import ru.mercuriev.game.of.life.no.condition.worlds.World;
import ru.mercuriev.game.of.life.no.condition.worlds.WrappedWorld;

import java.util.stream.IntStream;

import static org.junit.Assert.assertEquals;

public class NeighboursCounterTest implements Fixture {

    @Test
    public void testNeighboursAmount() {

        World build = World.fromArray(GENERATION);
        WrappedWorld w = WrappedWorld.newInstance(build);
        NeighboursCounter c = NeighboursCounter.forWorld(build);

        IntStream.range(0, NEIGHBOURS.length).forEach(i ->
                IntStream.range(0, NEIGHBOURS.length).forEach(j -> {
                    Cell cell = w.cellAt(i, j);
                    assertEquals(NEIGHBOURS[i][j], c.neighboursAmount(cell));
                        }
                )
        );
    }
}