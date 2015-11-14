package ru.mercuriev;

import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

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
    public void testStateInNextGeneration() throws Exception {
        for (int i = 0; i < GENERATION_AS_ARRAY.length; i++) {
            for (int j = 0; j < GENERATION_AS_ARRAY.length; j++) {

                int currentState = GENERATION_AS_ARRAY[i][j];
                int neighboursCount = new Counter(GENERATION_AS_ARRAY).countNeighbours(i, j);

                assertEquals(NEXT_GENERATION_AS_ARRAY[i][j], engine.stateInNextGeneration(currentState, neighboursCount));
            }
        }
    }

    @Test
    public void testCountNeighbours() throws Exception {
        for (int i = 0; i < GENERATION_AS_ARRAY.length; i++) {
            for (int j = 0; j < GENERATION_AS_ARRAY.length; j++) {
                assertEquals(NEIGHBOURS[i][j], new Counter(GENERATION_AS_ARRAY).countNeighbours(i, j));
            }
        }
        for (int i = 0; i < NEXT_GENERATION_AS_ARRAY.length; i++) {
            for (int j = 0; j < NEXT_GENERATION_AS_ARRAY.length; j++) {
                assertEquals(NEIGHBOURS_NEXT[i][j], new Counter(NEXT_GENERATION_AS_ARRAY).countNeighbours(i, j));
            }
        }
    }


}