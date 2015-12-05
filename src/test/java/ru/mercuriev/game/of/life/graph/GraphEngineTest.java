package ru.mercuriev.game.of.life.graph;

import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

/**
 * @author paul
 */
public class GraphEngineTest {

    @Test
    public void testNext() {

        GraphEngine engine = new GraphEngine();
        int[][] actualNextGeneration = engine.next(CellTestUtils.CURRENT_GENERATION);
        int[][] expectedNextGeneration = CellTestUtils.NEXT_GENERATION;

        for (int i = 0; i < expectedNextGeneration.length; i++) {
            assertEquals(actualNextGeneration[i],expectedNextGeneration[i]);
        }

    }

}
