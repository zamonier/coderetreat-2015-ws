package ru.mercuriev.game.of.life.groovy;

import org.junit.BeforeClass;
import org.junit.Test;
import ru.mercuriev.Fixture;
import ru.mercuriev.game.of.life.groovy.GEngine;

public class GEngineTest implements Fixture {

    private static GEngine engine;

    @BeforeClass
    public static void init() {
        engine = new GEngine();
    }

    @Test
    public void testNext() throws Exception {
        assertArrays2DEquals(NEXT_GENERATION, engine.next(GENERATION));
    }

}