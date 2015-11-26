package ru.mercuriev.game.of.life.no.condition;

import org.junit.Test;
import ru.mercuriev.Fixture;
import ru.mercuriev.game.of.life.no.condition.cells.AliveCell;
import ru.mercuriev.game.of.life.no.condition.cells.Cell;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Created by Eugene on 26.11.15.
 */
public class NeighboursCollectorTest implements Fixture {

    @Test
    public void testCollect() throws Exception {
        Cell c = new AliveCell(0, 0);
        World world = World.newInstance(GENERATION);
        NeighboursCollector collector = new NeighboursCollector(world);
        List<Cell> neighbours = collector.collect(world.cellAt(2, 0));
        assertNotNull(neighbours);
        assertEquals(8, neighbours.size());
    }


}