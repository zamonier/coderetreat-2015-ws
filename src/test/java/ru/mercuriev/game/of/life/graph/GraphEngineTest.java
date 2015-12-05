package ru.mercuriev.game.of.life.graph;

import org.testng.annotations.Test;
import ru.mercuriev.game.of.life.graph.cell.CellTestUtils;

import static org.testng.Assert.assertEquals;

/**
 * @author paul
 */
public class GraphEngineTest {

    static int[][] NEXT_GENERATION = {
            {0, 0, 0, 0, 0},
            {0, 0, 1, 0, 0},
            {0, 0, 0, 1, 1},
            {0, 0, 1, 1, 0},
            {0, 0, 0, 0, 0},
    };

    static int[][] CURRENT_GENERATION = {
            {0, 0, 0, 0, 0},
            {0, 0, 0, 1, 0},
            {0, 1, 0, 1, 0},
            {0, 0, 1, 1, 0},
            {0, 0, 0, 0, 0},
    };

    @Test
    public void testNext() {

        GraphEngine engine = new GraphEngine();
        int[][] actualNextGeneration = engine.next(CURRENT_GENERATION);
        int[][] expectedNextGeneration = NEXT_GENERATION;

        for (int i = 0; i < expectedNextGeneration.length; i++) {
            assertEquals(actualNextGeneration[i],expectedNextGeneration[i]);
        }

    }

}
