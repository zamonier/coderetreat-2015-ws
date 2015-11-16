package ru.mercuriev.game.of.life;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.assertEquals;

import ru.mercuriev.Fixture;
import ru.mercuriev.game.of.life.NeighboursCounter;

@RunWith(Parameterized.class)
public class NeighboursCounterTest implements Fixture {

    private NeighboursCounter counter;
    private int[][] neighbours;
    private int[][] generation;

    public NeighboursCounterTest(int[][] generation, int[][] neighbours) {
        this.neighbours = neighbours;
        this.generation = generation;
        this.counter = NeighboursCounter.getInstance(generation);
    }

    @Test
    public void testCount() throws Exception {
        for (int i = 0; i < generation.length; i++) {
            for (int j = 0; j < generation.length; j++) {
                assertEquals(neighbours[i][j], counter.countNeighbours(i, j));
            }
        }
    }

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {GENERATION, NEIGHBOURS},
                {NEXT_GENERATION, NEXT_NEIGHBOURS}
        });

    }
}