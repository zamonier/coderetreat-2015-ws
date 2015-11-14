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
        converter.init();
    }

    @Test
    public void testFromJson() throws IOException {
        assertArrays2DEquals(GENERATION, converter.fromJson(GENERATION_AS_JSON));
    }

    @Test
    public void testToJson() throws JsonProcessingException {
        assertEquals(GENERATION_AS_JSON, converter.toJson(GENERATION));
    }

}