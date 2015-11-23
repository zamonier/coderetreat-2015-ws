package ru.mercuriev.game.of.life;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.assertEquals;

import ru.mercuriev.Fixture;

@RunWith(Parameterized.class)
public class NeighboursCounterTest implements Fixture {

    private NeighboursCounter counter;
    private int[][] neighbours;
    private int[][] generation;

    public NeighboursCounterTest(int[][] generation, int[][] neighbours) {
        this.neighbours = neighbours;
        this.generation = generation;
        this.counter = NeighboursCounter.newInstance(generation);
    }

    @Test
    public void testCountNeighbours2(){
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