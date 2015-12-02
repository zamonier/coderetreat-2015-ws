package ru.mercuriev.game.of.life.no.condition.worlds;

import org.junit.Test;
import ru.mercuriev.Fixture;

import java.util.stream.IntStream;

import static org.junit.Assert.assertEquals;

public class WrappedWorldTest implements Fixture {

    @Test
    public void testWrapping() throws Exception {

        WrappedWorld world = WrappedWorld.newInstance(World.fromArray(GENERATION));

        IntStream.range(0, GENERATION.length).forEach(i ->
                IntStream.range(0, GENERATION.length).forEach(j ->
                        assertEquals(GENERATION[i][j], world.cellAt(i, j).getState())
                )
        );
    }


}