package ru.mercuriev;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertEquals;

public class ConverterTest implements Fixture {

    private static Converter converter;

    @BeforeClass
    public static void init() {
        converter = new Converter();
    }

    @Test
    public void testFromJson() throws IOException {
        assertArrays2DEquals(NEXT_GENERATION_AS_ARRAY, converter.fromJson(NEXT_GENERATION_AS_JSON));
    }

    @Test
    public void testToJson() throws JsonProcessingException {
        assertEquals(NEXT_GENERATION_AS_JSON, converter.toJson(NEXT_GENERATION_AS_ARRAY));
    }

}