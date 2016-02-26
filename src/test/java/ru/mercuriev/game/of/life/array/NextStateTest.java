package ru.mercuriev.game.of.life.array;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import ru.mercuriev.Fixture;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class NextStateTest implements Fixture {

    @Parameterized.Parameter
    public int currentState;

    @Parameterized.Parameter(value = 1)
    public int neighbours;

    @Parameterized.Parameter(value = 2)
    public int nextState;

    private ArrayEngine arrayEngine = new ArrayEngine();

    @Test
    public void testNextState() {
        assertEquals(nextState, arrayEngine.nextState(currentState, neighbours));
    }

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(NEXT_STATES);
    }

}