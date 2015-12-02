package ru.mercuriev.game.of.life.no.condition;

import org.junit.Test;
import ru.mercuriev.Fixture;

import java.util.stream.IntStream;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class WrappedWorldTest implements Fixture {

    @Test
    public void testNewInstance() throws Exception {
        WrappedWorld build = WrappedWorld.newInstance(GENERATION);
        WrappedWorld w = WrappedWorld.newInstance(build);
        assertNotNull(w);
    }

    @Test
    public void testSize() throws Exception {
        WrappedWorld build = WrappedWorld.newInstance(GENERATION);
        WrappedWorld w = WrappedWorld.newInstance(build);
        assertEquals(GENERATION.length, w.size());
    }

    @Test
    public void testCells() throws Exception {
        WrappedWorld build = WrappedWorld.newInstance(GENERATION);
        WrappedWorld w = WrappedWorld.newInstance(build);
        IntStream.range(0, GENERATION.length).forEach(i ->
                IntStream.range(0, GENERATION.length).forEach(j ->
                        assertEquals(GENERATION[i][j], w.cellAt(i, j).getState())
                )
        );
    }


}