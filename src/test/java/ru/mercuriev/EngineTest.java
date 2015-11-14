package ru.mercuriev;

import org.junit.BeforeClass;
import org.junit.Test;

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
        assertArrays2DEquals(NEXT_GENERATION, engine.nextGeneration(GENERATION));
    }

}