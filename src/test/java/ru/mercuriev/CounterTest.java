package ru.mercuriev;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class CounterTest implements Fixture {

    private Counter counter;
    private int[][] neighbours;
    private int[][] generation;

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {GENERATION_AS_ARRAY, NEIGHBOURS},
                {NEXT_GENERATION_AS_ARRAY, NEIGHBOURS_NEXT}
        });

    }

    public CounterTest(int[][] generation, int[][] neighbours) {
        this.neighbours = neighbours;
        this.generation = generation;
        this.counter = new Counter(generation);
    }

    @Test
    public void testCountNeighbours() throws Exception {
        for (int i = 0; i < generation.length; i++) {
            for (int j = 0; j < generation.length; j++) {
                assertEquals(neighbours[i][j], counter.countNeighbours(i, j));
            }
        }

    }
}