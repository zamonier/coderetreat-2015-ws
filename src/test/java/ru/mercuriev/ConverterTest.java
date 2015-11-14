package ru.mercuriev;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.*;

/**
 * Created by Eugene on 15.11.15.
 */
public class ConverterTest {

    public static final String GENERATION = "3,1,0,0,1,1,0,1,1,1";
    public static final String NEXT_GENERATION = "[[1,1,0],[0,0,1],[1,0,1]]";
    public static final int[][] INPUT_ARRAY = {{1, 0, 0}, {1, 1, 0}, {1, 1, 1}};
    public static final int[][] OUTPUT_ARRAY = {{1, 1, 0}, {0, 0, 1}, {1, 0, 1}};

    Converter converter = new Converter();

    @Test
    public void testParse() throws Exception {
        int[][] parse = converter.parse(GENERATION);
        int numOfRow = 0;
        for (int[] row : parse) {
            Arrays.equals(INPUT_ARRAY[numOfRow++], row);
        }
    }


    @Test
    public void testPrint() throws JsonProcessingException {
        String print = converter.print(OUTPUT_ARRAY);
        assertEquals(NEXT_GENERATION, print);
    }

}