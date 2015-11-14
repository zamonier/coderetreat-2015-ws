package ru.mercuriev;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Arrays;

import static org.junit.Assert.*;

/**
 * Created by Eugene on 15.11.15.
 */
public class GameOfLiveTest {

    public static final String GENERATION = "3,1,0,0,1,1,0,1,1,1";
    public static final String NEXT_GENERATION = "[[1,1,0],[0,0,1],[1,0,1]]";
    public static final int[][] INPUT_ARRAY = {{1, 0, 0}, {1, 1, 0}, {1, 1, 1}};
    public static final int[][] OUTPUT_ARRAY = {{1, 1, 0}, {0, 0, 1}, {1, 0, 1}};

    GameOfLive gameOfLive = new GameOfLive();

    @Test
    public void testNextGen() throws Exception {
        String nextGen = gameOfLive.nextGen(GENERATION);
        assertEquals(NEXT_GENERATION, nextGen);
    }

    @Test
    public void testParse() throws Exception {
        int[][] parse = gameOfLive.parse(GENERATION);
        int numOfRow = 0;
        for (int[] row : parse) {
            Arrays.equals(INPUT_ARRAY[numOfRow++], row);
        }
    }

    @Test
    public void testCalculate() throws Exception {
        int[][] calculated = gameOfLive.calculate(INPUT_ARRAY);
        int numOfRow = 0;
        for (int[] row : calculated) {
            assertTrue(Arrays.equals(OUTPUT_ARRAY[numOfRow++], row));
        }
    }

    @Test
    public void testIsAlive() throws Exception {
        for(int i = 0 ; i < INPUT_ARRAY.length; i++){
            for(int j = 0; j < INPUT_ARRAY.length; j++){
                int alive = gameOfLive.isAlive(INPUT_ARRAY, i, j);
                assertEquals(OUTPUT_ARRAY[i][j], alive);
            }
        }
    }

    @Test
    public void testPrint() throws JsonProcessingException {
        String print = gameOfLive.print(OUTPUT_ARRAY);
        assertEquals(NEXT_GENERATION, print);
    }
}