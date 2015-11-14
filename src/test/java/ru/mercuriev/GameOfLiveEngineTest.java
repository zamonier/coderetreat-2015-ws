package ru.mercuriev;

import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.*;

/**
 * Created by Eugene on 15.11.15.
 */
public class GameOfLiveEngineTest {

    public static final int[][] INPUT_ARRAY = {{1, 0, 0}, {1, 1, 0}, {1, 1, 1}};
    public static final int[][] OUTPUT_ARRAY = {{1, 1, 0}, {0, 0, 1}, {1, 0, 1}};

    GameOfLiveEngine engine = new GameOfLiveEngine();

    @Test
    public void testCalculate() throws Exception {
        int[][] calculated = engine.calculate(INPUT_ARRAY);
        int numOfRow = 0;
        for (int[] row : calculated) {
            assertTrue(Arrays.equals(OUTPUT_ARRAY[numOfRow++], row));
        }
    }

    @Test
    public void testIsAlive() throws Exception {
        for(int i = 0 ; i < INPUT_ARRAY.length; i++){
            for(int j = 0; j < INPUT_ARRAY.length; j++){
                int alive = engine.isAlive(INPUT_ARRAY, i, j);
                assertEquals(OUTPUT_ARRAY[i][j], alive);
            }
        }
    }

}