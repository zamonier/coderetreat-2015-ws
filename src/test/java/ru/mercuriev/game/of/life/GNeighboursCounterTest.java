package ru.mercuriev.game.of.life;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import ru.mercuriev.Fixture;

import java.util.Arrays;
import java.util.Collection;

@RunWith(Parameterized.class)
public class GNeighboursCounterTest implements Fixture {

    private GNeighboursCounter counter;
    private int[][] neighbours;
    private int[][] generation;

    public GNeighboursCounterTest(int[][] generation, int[][] neighbours) {
        this.neighbours = neighbours;
        this.generation = generation;
        this.counter = GNeighboursCounter.newInstance(generation);
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