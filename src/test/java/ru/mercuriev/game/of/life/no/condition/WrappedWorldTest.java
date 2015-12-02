package ru.mercuriev.game.of.life.no.condition;

import org.junit.Test;
import ru.mercuriev.Fixture;
import ru.mercuriev.game.of.life.no.condition.worlds.World;
import ru.mercuriev.game.of.life.no.condition.worlds.WrappedWorld;

import java.util.stream.IntStream;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class WrappedWorldTest implements Fixture {

    @Test
    public void testNewInstance() throws Exception {
        World build = World.fromArray(GENERATION);
        WrappedWorld w = WrappedWorld.newInstance(build);
        assertNotNull(w);
    }

    @Test
    public void testCells() throws Exception {
        World build = World.fromArray(GENERATION);
        WrappedWorld w = WrappedWorld.newInstance(build);
        IntStream.range(0, GENERATION.length).forEach(i ->
                IntStream.range(0, GENERATION.length).forEach(j ->
                        assertEquals(GENERATION[i][j], w.cellAt(i, j).getState())
                )
        );
    }


}