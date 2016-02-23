package ru.mercuriev.game.of.life.no.condition;

import org.junit.Test;
import ru.mercuriev.Fixture;
import ru.mercuriev.game.of.life.no.condition.cells.Cell;
import ru.mercuriev.game.of.life.no.condition.worlds.World;
import ru.mercuriev.game.of.life.no.condition.worlds.WrappedWorld;

import java.util.stream.IntStream;

import static org.junit.Assert.assertEquals;

public class DecisionMakerTest implements Fixture {

    @Test
    public void testNextGenCell() throws Exception {

        WrappedWorld world = WrappedWorld.newInstance(World.fromArray(GENERATION));

        IntStream.range(0, NEXT_GENERATION.length).forEach(i -> {
            IntStream.range(0, NEXT_GENERATION.length).forEach(j -> {

                Cell cell = DecisionMaker.nextGenerationCell(world.cellAt(i, j), NEIGHBOURS[i][j]);

                assertEquals(NEXT_GENERATION[i][j], cell.getState());
            });
        });
    }
}