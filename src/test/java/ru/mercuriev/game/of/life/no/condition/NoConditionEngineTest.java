package ru.mercuriev.game.of.life.no.condition;

import org.junit.BeforeClass;
import org.junit.Test;
import ru.mercuriev.Fixture;

public class NoConditionEngineTest implements Fixture {

    private static NoConditionEngine engine;

    @BeforeClass
    public static void init() {
        engine = new NoConditionEngine();
    }

    @Test
    public void testNext() throws Exception {
        assertArrays2DEquals(NEXT_GENERATION, engine.next(GENERATION));
    }

}