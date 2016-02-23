package ru.mercuriev.game.of.life.array;

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

    private static ArrayEngine arrayEngine;

    @BeforeClass
    public static void init() {
        arrayEngine = new ArrayEngine();
    }

    public NextStateTest(int currentState, int neighbours, int nextState) {
        this.currentState = currentState;
        this.neighbours = neighbours;
        this.nextState = nextState;
    }

    @Test
    public void testNextState() throws Exception {
        assertEquals(nextState, arrayEngine.nextState(currentState, neighbours));
    }

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(NEXT_STATES);
    }

}