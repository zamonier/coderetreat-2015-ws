package ru.mercuriev;

import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by Eugene on 15.11.15.
 */
public class GameOfLiveEngineTest implements Fixture {

    GameOfLiveEngine engine = new GameOfLiveEngine();

    @Test
    public void testCalculate() throws Exception {
        int[][] calculated = engine.calculate(GENERATION_AS_ARRAY);
        int numOfRow = 0;
        for (int[] row : calculated) {
            assertTrue(Arrays.equals(NEXT_GENERATION_AS_ARRAY[numOfRow++], row));
        }
    }

    @Test
    public void testIsAlive() throws Exception {
        for (int i = 0; i < GENERATION_AS_ARRAY.length; i++) {
            for (int j = 0; j < GENERATION_AS_ARRAY.length; j++) {
                int alive = engine.isAlive(GENERATION_AS_ARRAY, i, j);
                assertEquals(NEXT_GENERATION_AS_ARRAY[i][j], alive);
            }
        }
    }

}