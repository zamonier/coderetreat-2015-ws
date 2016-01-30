package ru.mercuriev.game.of.life;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import ru.mercuriev.Fixture;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class NextStateTest implements Fixture {

    private int currentState;
    private int neighbours;
    private int nextState;

    private static Engine engine;

    @BeforeClass
    public static void init() {
        engine = new Engine();
    }

    public NextStateTest(int currentState, int neighbours, int nextState) {
        this.currentState = currentState;
        this.neighbours = neighbours;
        this.nextState = nextState;
    }

    @Test
    public void testNextState() throws Exception {
        assertEquals(nextState, engine.nextState(currentState, neighbours));
    }

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(NEXT_STATES);
    }

}