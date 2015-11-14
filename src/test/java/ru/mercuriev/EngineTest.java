package ru.mercuriev;

import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by Eugene on 15.11.15.
 */
public class EngineTest implements Fixture {

    private static Engine engine;

    @BeforeClass
    public static void init() {
        engine = new Engine();
    }

    @Test
    public void testNextGeneration() throws Exception {
        assertArrays2DEquals(NEXT_GENERATION_AS_ARRAY, engine.nextGeneration(GENERATION_AS_ARRAY));
    }

    @Test
    public void testCellInNextGeneration() throws Exception {
        for (int i = 0; i < GENERATION_AS_ARRAY.length; i++) {
            for (int j = 0; j < GENERATION_AS_ARRAY.length; j++) {
                int cellInNextGeneration = engine.cellInNextGeneration(i, j, GENERATION_AS_ARRAY);
                assertEquals(NEXT_GENERATION_AS_ARRAY[i][j], cellInNextGeneration);
            }
        }
    }

}