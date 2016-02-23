package ru.mercuriev.game.of.life.array;

import org.junit.BeforeClass;
import org.junit.Test;
import ru.mercuriev.Fixture;

public class ArrayEngineTest implements Fixture {

    private static ArrayEngine arrayEngine;

    @BeforeClass
    public static void init() {
        arrayEngine = new ArrayEngine();
    }

    @Test
    public void testNext() throws Exception {
        assertArrays2DEquals(NEXT_GENERATION, arrayEngine.next(GENERATION));
    }

}