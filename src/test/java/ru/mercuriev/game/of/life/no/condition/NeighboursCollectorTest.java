package ru.mercuriev.game.of.life.no.condition;

import org.junit.Test;
import ru.mercuriev.Fixture;
import ru.mercuriev.game.of.life.no.condition.worlds.World;
import ru.mercuriev.game.of.life.no.condition.worlds.WrappedWorld;

import java.util.stream.IntStream;

import static org.junit.Assert.assertEquals;

public class NeighboursCollectorTest implements Fixture {

    @Test
    public void testCollectFull() {
        World build = World.fromArray(GENERATION);
        WrappedWorld w = WrappedWorld.newInstance(build);
        NeighboursCollector c = new NeighboursCollector(build);
        IntStream.range(0, NEIGHBOURS.length).forEach(i ->
                IntStream.range(0, NEIGHBOURS.length).forEach(j -> {
                    assertEquals(NEIGHBOURS[i][j], c.neighboursAmount(w.cellAt(i, j)));
                        }
                )
        );
    }
}