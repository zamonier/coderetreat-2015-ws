package ru.mercuriev;

import static org.junit.Assert.assertArrayEquals;

/**
 * Created by Eugene on 15.11.15.
 */
public interface Fixture {

    String GENERATION_AS_JSON = "[[1,0,0],[1,1,0],[1,1,1]]";
    int[][] GENERATION_AS_ARRAY = {{1, 0, 0}, {1, 1, 0}, {1, 1, 1}};
    String NEXT_GENERATION_AS_JSON = "[[1,1,0],[0,0,1],[1,0,1]]";
    int[][] NEXT_GENERATION_AS_ARRAY = {{1, 1, 0}, {0, 0, 1}, {1, 0, 1}};

    default void assertArrays2DEquals(int[][] expected, int[][] actual) {
        int rowIndex = 0;
        for (int[] row : expected) {
            assertArrayEquals(actual[rowIndex++], row);
        }
    }

}
