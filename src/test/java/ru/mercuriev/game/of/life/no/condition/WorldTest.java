package ru.mercuriev.game.of.life.no.condition;

import org.junit.Test;
import ru.mercuriev.Fixture;

import java.util.stream.IntStream;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class WorldTest implements Fixture {

    @Test
    public void testNewInstance() throws Exception {
        World w = World.newInstance(GENERATION);
        assertNotNull(w);
    }

    @Test
    public void testSize() throws Exception {
        World w = World.newInstance(GENERATION);
        assertEquals(GENERATION.length, w.size());
    }

    @Test
    public void testCells() throws Exception {
        World w = World.newInstance(GENERATION);
        IntStream.range(0, GENERATION.length).forEach(i ->
                IntStream.range(0, GENERATION.length).forEach(j ->
                        assertEquals(GENERATION[i][j], w.cellAt(i, j).getState())
                )
        );
    }


}