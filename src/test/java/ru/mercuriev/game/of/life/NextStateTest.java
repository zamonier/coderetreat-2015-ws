package ru.mercuriev.game.of.life;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import ru.mercuriev.Fixture;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.assertEquals;
import static ru.mercuriev.game.of.life.Engine.ALIVE;
import static ru.mercuriev.game.of.life.Engine.DEAD;

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
        return Arrays.asList(new Object[][]{
                {ALIVE, 0, DEAD},
                {ALIVE, 1, DEAD},
                {ALIVE, 2, ALIVE},
                {ALIVE, 3, ALIVE},
                {ALIVE, 4, DEAD},
                {ALIVE, 5, DEAD},
                {DEAD, 0, DEAD},
                {DEAD, 1, DEAD},
                {DEAD, 2, DEAD},
                {DEAD, 3, ALIVE},
                {DEAD, 4, DEAD},
                {DEAD, 5, DEAD},
        });
    }

}