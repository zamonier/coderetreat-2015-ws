package ru.mercuriev.game.of.life.no.condition;

import org.junit.Test;
import ru.mercuriev.Fixture;
import ru.mercuriev.game.of.life.no.condition.cells.Cell;

import java.util.List;
import java.util.stream.IntStream;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class NeighboursCollectorTest implements Fixture {

    @Test
    public void testCollect() throws Exception {
        World build = WrappedWorld.newInstance(GENERATION);
        WrappedWorld wrappedWorld = WrappedWorld.newInstance(build);
        NeighboursCollector collector = new NeighboursCollector(wrappedWorld);
        List<Cell> neighbours = collector.collect(wrappedWorld.cellAt(2, 0));
        assertNotNull(neighbours);
        assertEquals(8, neighbours.size());
    }

    @Test
    public void testCollectFull() {
        World build = WrappedWorld.newInstance(GENERATION);
        WrappedWorld w = WrappedWorld.newInstance(build);
        NeighboursCollector c = new NeighboursCollector(w);
        IntStream.range(0, w.size()).forEach(i ->
                IntStream.range(0, w.size()).forEach(j -> {
                            int amount = c.collect(w.cellAt(i, j)).stream().mapToInt(Cell::getState).sum();
                            assertEquals(NEIGHBOURS[i][j], amount);
                        }
                )
        );
    }
}