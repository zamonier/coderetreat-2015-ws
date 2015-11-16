package ru.mercuriev;

import org.junit.BeforeClass;
import org.junit.Test;
import ru.mercuriev.game.of.life.Engine;

public class EngineTest implements Fixture {

    private static Engine engine;

    @BeforeClass
    public static void init() {
        engine = new Engine();
    }

    @Test
    public void testNext() throws Exception {
        assertArrays2DEquals(NEXT_GENERATION, engine.getNextGeneration(GENERATION));
    }

}