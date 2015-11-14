package ru.mercuriev;

import static org.junit.Assert.assertArrayEquals;

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

    default void assertArrays2DEquals(int[][] expected, int[][] actual) {
        int rowIndex = 0;
        for (int[] row : expected) {
            assertArrayEquals(row, actual[rowIndex++]);
        }
    }

}
