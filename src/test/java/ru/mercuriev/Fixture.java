package ru.mercuriev;

import static org.junit.Assert.assertArrayEquals;
import static ru.mercuriev.game.of.life.array.ArrayEngine.ALIVE;
import static ru.mercuriev.game.of.life.array.ArrayEngine.DEAD;

public interface Fixture {

    String GENERATION_AS_JSON = "[[1,0,0],[1,1,0],[1,1,1]]";
    int[][] GENERATION = {
            {1, 0, 0},
            {1, 1, 0},
            {1, 1, 1}};
    int[][] NEIGHBOURS = {
            {2, 3, 1},
            {4, 5, 3},
            {3, 4, 2}};

    String NEXT_GENERATION_AS_JSON = "[[1,1,0],[0,0,1],[1,0,1]]";
    int[][] NEXT_GENERATION = {
            {1, 1, 0},
            {0, 0, 1},
            {1, 0, 1}};
    int[][] NEXT_NEIGHBOURS = {
            {1, 2, 2},
            {3, 5, 2},
            {0, 3, 1}};

    Object[][] NEXT_STATES = {
            {ALIVE, 0, DEAD},
            {ALIVE, 1, DEAD},
            {ALIVE, 2, ALIVE},
            {ALIVE, 3, ALIVE},
            {ALIVE, 4, DEAD},
            {ALIVE, 5, DEAD},
            {DEAD, 0, DEAD},
            {DEAD, 1, DEAD},
            {DEAD, 2, DEAD},
            {DEAD, 3, ALIVE},
            {DEAD, 4, DEAD},
            {DEAD, 5, DEAD},
    };


    default void assertArrays2DEquals(int[][] expected, int[][] actual) {
        int rowIndex = 0;
        for (int[] row : expected) {
            assertArrayEquals(row, actual[rowIndex++]);
        }
    }

}
