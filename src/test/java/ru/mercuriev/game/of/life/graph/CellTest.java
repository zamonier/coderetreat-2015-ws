package ru.mercuriev.game.of.life.graph;


import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;
import static ru.mercuriev.game.of.life.graph.CellTestUtils.construct;

/**
 * @author paul
 */
public class CellTest {

    private int[][] CURRENT_GENERATION = {
            {0, 0, 0, 0, 0},
            {0, 0, 0, 1, 0},
            {0, 1, 0, 1, 0},
            {0, 0, 1, 1, 0},
            {0, 0, 0, 0, 0},
    };
    private Stream<IntStream> CURRENT_GENERATION_STREAM = Arrays.stream(CURRENT_GENERATION).map(Arrays::stream);

    private int[][] NEXT_GENERATION = {
            {0, 0, 0, 0, 0},
            {0, 0, 1, 0, 0},
            {0, 0, 0, 1, 1},
            {0, 0, 1, 1, 0},
            {0, 0, 0, 0, 0},
    };
    private Stream<IntStream> NEXT_GENERATION_STREAM = Arrays.stream(NEXT_GENERATION).map(Arrays::stream);

    @DataProvider
    public Object[][] getCalculateNextStateData() {
        return new Object[][]{
                {Cell.ALIVE, 0, Cell.DEAD},
                {Cell.ALIVE, 1, Cell.DEAD},
                {Cell.ALIVE, 2, Cell.ALIVE},
                {Cell.ALIVE, 3, Cell.ALIVE},
                {Cell.ALIVE, 4, Cell.DEAD},
                {Cell.ALIVE, 5, Cell.DEAD},
                {Cell.ALIVE, 6, Cell.DEAD},
                {Cell.ALIVE, 7, Cell.DEAD},
                {Cell.ALIVE, 8, Cell.DEAD},
                {Cell.DEAD, 0, Cell.DEAD},
                {Cell.DEAD, 1, Cell.DEAD},
                {Cell.DEAD, 2, Cell.DEAD},
                {Cell.DEAD, 3, Cell.ALIVE},
                {Cell.DEAD, 4, Cell.DEAD},
                {Cell.DEAD, 5, Cell.DEAD},
                {Cell.DEAD, 6, Cell.DEAD},
                {Cell.DEAD, 7, Cell.DEAD},
                {Cell.DEAD, 8, Cell.DEAD},
        };
    }

    @Test(dataProvider = "getCalculateNextStateData")
    public void testCalculateNextState(int currentState, int countOfLiveNeighbours, int expectedNextState) {

        Cell current = new Cell();
        current.state = currentState;
        int actualNextState = Cell.calculateNextState(current, countOfLiveNeighbours);
        assertEquals(actualNextState, expectedNextState);

    }

    @DataProvider
    public Object[][] getTestLineToStreamData() {
        return new Object[][]{
                {construct(0, 1, 0, 1, 1), "0 1 0 1 1"},
                {construct(0), "0"},
                {construct(), ""},
        };
    }

    @Test(dataProvider = "getTestLineToStreamData")
    public void testLineToStream(CellHolder cellHolder, String expectedAsString) {

        Cell current = cellHolder.cell;

        if (current != null) {
            while (current.left != null)
                current = current.left;
        }

        String actualValue = Cell.lineToStream(current, cell -> cell.state)
                                 .mapToObj(value -> "" + value)
                                 .collect(Collectors.joining(" "));
        assertEquals(actualValue, expectedAsString);

    }

    @DataProvider
    public Object[][] getTestGetNextLineCellSingleLineData() {
        return new Object[][]{
                {construct(0, 1, 0, 1, 1)},
                {construct(0)},
                {construct()},
        };
    }

    @Test(dataProvider = "getTestGetNextLineCellSingleLineData")
    public void testGetNextLineCellSingleLine(CellHolder cellHolder) {

        Cell current = cellHolder.cell;

        Cell actualNextLineCell = Cell.getNextLineCell(current);
        assertEquals(actualNextLineCell, null);

    }

    @DataProvider
    public Object[][] getTestGetNextLineCellMultiLineData() {
        return new Object[][]{
                {construct(0, 1, 0, 1, 1)},
                {construct(0)},
        };
    }

    @SuppressWarnings("SuspiciousNameCombination")
    @Test(dataProvider = "getTestGetNextLineCellMultiLineData")
    public void testGetNextLineCellMultiLine(CellHolder cellHolder) {

        Cell current = cellHolder.cell;

        Cell expectedLeft = cellHolder.cell;
        while (expectedLeft.left != null)
            expectedLeft = expectedLeft.left;

        // set additional bottom cell for the left most
        Cell expectedBottom = new Cell(1);
        expectedBottom.top = expectedLeft;
        expectedLeft.bottom = expectedBottom;

        Cell actualBottom = Cell.getNextLineCell(current);
        assertNotNull(actualBottom);
        Cell actualLeft = actualBottom.top;

        assertEquals(actualBottom, expectedBottom);
        assertEquals(actualLeft, expectedLeft);

    }

}
