package ru.mercuriev.game.of.life.array;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import ru.mercuriev.Fixture;

import java.util.Arrays;
import java.util.Collection;

@RunWith(Parameterized.class)
public class NeighboursCounterTest implements Fixture {

    @Parameterized.Parameter
    public int[][] generation;

    @Parameterized.Parameter(value = 1)
    public int[][] neighbours;

    private NeighboursCounter counter;

    @Before
    public void init() {
        counter = NeighboursCounter.newInstance(generation);
    }

    @Test
    public void testCountNeighbours() {
        assertArrays2DEquals(neighbours, counter.countNeighbours());
    }

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {GENERATION, NEIGHBOURS},
                {NEXT_GENERATION, NEXT_NEIGHBOURS}
        });

    }
}