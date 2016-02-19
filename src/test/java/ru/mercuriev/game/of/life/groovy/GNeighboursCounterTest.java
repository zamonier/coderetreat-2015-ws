package ru.mercuriev.game.of.life.groovy;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import ru.mercuriev.Fixture;
import ru.mercuriev.game.of.life.groovy.GNeighboursCounter;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class GNeighboursCounterTest implements Fixture {

    private GNeighboursCounter counter;
    private int[][] neighbours;

    public GNeighboursCounterTest(int[][] generation, int[][] neighbours) {
        this.neighbours = neighbours;
        this.counter = new GNeighboursCounter(generation);
    }

    @Test
    public void testCountNeighbours() {
        for (int i = 0; i < neighbours.length; i++) {
            for (int j = 0; j < neighbours.length; j++) {
                assertEquals(neighbours[i][j], counter.cellNeighborsAmount(i, j));
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