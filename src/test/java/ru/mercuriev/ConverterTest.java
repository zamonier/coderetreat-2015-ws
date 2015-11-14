package ru.mercuriev;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.Test;

import java.io.IOException;
import java.util.Arrays;

import static org.junit.Assert.assertEquals;

/**
 * Created by Eugene on 15.11.15.
 */
public class ConverterTest implements Fixture {

    Converter converter = new Converter();

    @Test
    public void testPrint() throws JsonProcessingException {
        String print = converter.toJson(NEXT_GENERATION_AS_ARRAY);
        assertEquals(NEXT_GENERATION_AS_JSON, print);
    }

    @Test
    public void testParseJson() throws IOException {
        int[][] arr = converter.fromJson(NEXT_GENERATION_AS_JSON);
        int numOfRow = 0;
        for (int[] row : arr) {
            Arrays.equals(NEXT_GENERATION_AS_ARRAY[numOfRow++], row);
        }
    }

}