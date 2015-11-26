package ru.mercuriev.game.of.life.no.condition;

import org.junit.Test;
import ru.mercuriev.Fixture;
import ru.mercuriev.game.of.life.no.condition.cells.AliveCell;
import ru.mercuriev.game.of.life.no.condition.cells.Cell;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.Assert.assertEquals;

/**
 * Created by Eugene on 24.11.15.
 */
public class Test1 implements Fixture {

    @Test
    public void test() {

        // cell store i and j
        // collect list of neighbours
        // sum them
        // look at map

    }

    @Test
    public void testCell() {
        int i = 0, j = 0;
        Cell c = new AliveCell(i, j);
        assertEquals(i, c.getX());
        assertEquals(j, c.getY());
    }

    @Test
    public void testCellState() {
        Cell c = new AliveCell(0, 0);
        assertEquals(Cell.ALIVE, c.getState());
    }

    @Test
    public void testNeighboursSum() {
        List<Cell> neighbours = new ArrayList<>();
        assertEquals(0, neighbours.stream().mapToInt(n -> n.getState()).sum());
    }

    @Test
    public void testNextGen() {
        Cell c = new AliveCell(0, 0);
        int neighboursAmount = 3;
        Cell nextGen = DisisionMaker.nextGenCell(c, neighboursAmount);
    }

    @Test
    public void testStream() {
        List<Cell> cells = new ArrayList<>();

        List<Cell> nextGen = cells.stream()
                .map(c ->
                        new Object[]{
                                c,
                                new NeighboursCollector(null).collect(c).stream().mapToInt(n -> n.getState()).sum()
                        }
                )
                .map(
                        o -> DisisionMaker.nextGenCell((Cell) o[0], (Integer) o[1])
                ).collect(Collectors.toList());
    }
}
