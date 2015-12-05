package ru.mercuriev.game.of.life.graph;


import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;
import static ru.mercuriev.game.of.life.graph.CellTestUtils.*;

/**
 * @author paul
 */
public class CellTest {

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
                {new int[] {0, 1, 0, 1, 1}, "0 1 0 1 1"},
                {new int[] {0}, "0"},
                {new int[] {}, ""},
        };
    }

    @Test(dataProvider = "getTestLineToStreamData")
    public void testLineToStream(int[] array, String expectedAsString) {

        CellHolder cellHolder = constructLine(array);
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
                {new int[] {0, 1, 0, 1, 1}},
                {new int[] {0}},
                {new int[] {}},
        };
    }

    @Test(dataProvider = "getTestGetNextLineCellSingleLineData")
    public void testGetNextLineCellSingleLine(int[] array) {

        CellHolder cellHolder = constructLine(array);
        Cell current = cellHolder.cell;

        Cell actualNextLineCell = Cell.getNextLineCell(current);
        assertEquals(actualNextLineCell, null);

    }

    @DataProvider
    public Object[][] getTestGetNextLineCellMultiLineData() {
        return new Object[][]{
                {new int[] {0, 1, 0, 1, 1}},
                {new int[] {0}},
        };
    }

    // TODO rename remove warning
    @SuppressWarnings("SuspiciousNameCombination")
    @Test(dataProvider = "getTestGetNextLineCellMultiLineData")
    public void testGetNextLineCellMultiLine(int[] array) {

        CellHolder cellHolder = constructLine(array);
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

    @DataProvider
    public Object[][] getTestCellToStreamData() {
        return new Object[][] {
                {new int[][] {{0,1,0},{0,0,0},{1,1,1}},"<[0 1 0]-[0 0 0]-[1 1 1]>"},
                {new int[][] {{0,0},{1,1}},"<[0 0]-[1 1]>"},
                {new int[][] {{0}}, "<[0]>"},
                {new int[][] {{}}, "<>"},
        };
    }

    @Test(dataProvider = "getTestCellToStreamData")
    public void testCellToStream(int[][] values, String expectedValue) {

        Cell current = constructMultiline(values);

        Stream<IntStream> stream = Cell.cellToStream(current,cell -> cell.state);

        // TODO replace to toString();
        String actualValue =
                stream.map(intStream -> intStream.mapToObj(value -> "" + value)
                                                 .collect(Collectors.joining(" ", "[", "]")))
                      .collect(Collectors.joining("-", "<", ">"));

        assertEquals(actualValue,expectedValue);

    }

    @DataProvider
    public Object[][] getTesGetCountOfNeighboursAliveData() {
        return new Object[][] {
                {null,null,null,/**/null,/**/null,null,null,/**/null,/**/0},
                {1,1,1,/**/1,/**/1,1,1,/**/1,/**/8},
                {0,1,0,/**/1,/**/0,1,0,/**/1,/**/4},
                {0,null,0,/**/1,/**/0,1,0,/**/1,/**/3},
                {0,null,0,/**/1,/**/0,null,0,/**/0,/**/1},
                {null,null,null,/**/1,/**/1,1,null,/**/null,/**/3}, // left top corner
                {null,null,null,/**/null,/**/null,0,1,/**/0,/**/1}, // right top corner
                {0,1,null,/**/null,/**/null,null,null,/**/1,/**/2}, // right bottom corner
                {null,1,0,/**/1,/**/null,null,null,/**/null,/**/2}, // left bottom corner
                {null,1,1,/**/1,/**/0,0,null,/**/null,/**/3}, // left edge
                {null,null,null,/**/1,/**/0,0,1,/**/0,/**/2}, // top edge
                {0,1,null,/**/null,/**/null,0,1,/**/1,/**/3}, // right edge
                {1,1,1,/**/1,/**/null,null,null,/**/1,/**/5}, // bottom edge
        };
    }

    @Test(dataProvider = "getTesGetCountOfNeighboursAliveData")
    public void tesGetCountOfNeighboursAlive(Integer leftTopValue, Integer topValue, Integer rightTopValue,
                                             Integer rightValue,
                                             Integer rightBottomValue, Integer bottomValue, Integer leftBottomValue,
                                             Integer leftValue,
                                             int expectedCount) {

        Cell central = new Cell(0);

        if (topValue != null)  {
            central.top = new Cell(topValue);

            if (leftTopValue != null) {
                central.top.left = new Cell(leftTopValue);
            }

            if (rightTopValue != null) {
                central.top.right = new Cell(rightTopValue);
            }

        }

        if (bottomValue != null)  {
            central.bottom = new Cell(bottomValue);

            if (leftBottomValue != null) {
                central.bottom.left = new Cell(leftBottomValue);
            }

            if (rightBottomValue != null) {
                central.bottom.right = new Cell(rightBottomValue);
            }

        }

        if (leftValue != null) {
            central.left = new Cell(leftValue);
        }

        if (rightValue != null) {
            central.right = new Cell(rightValue);
        }

        int actualCount = Cell.getCountOfNeighboursAlive(central);
        assertEquals(actualCount, expectedCount);

    }

    @DataProvider
    public Object[][] getTestGetStreamOfLinesData() {
        return new Object[][] {
                {new int[][] {{0,1,0,1,1}, {1,1,1,0,0}, {0,0,1,0,0}, {0,0,0,0,0}, {1,1,1,1,1}},
                            "<[0 1 0 1 1]><[1 1 1 0 0]><[0 0 1 0 0]><[0 0 0 0 0]><[1 1 1 1 1]>"},
                {new int[][] {{0,1,0}, {1,1,1}, {0,0,1}},
                            "<[0 1 0]><[1 1 1]><[0 0 1]>"},
                {new int[][] {{},{1,1,1}, {0}},
                      "<[empty]><[1 1 1]><[0]>"},
        };
    }

    @Test(dataProvider = "getTestGetStreamOfLinesData")
    public void testGetStreamOfLines(int[][] array, String expectedValue) {

        Stream<IntStream> inputStream = Arrays.stream(array).map(Arrays::stream);

        Stream<CellHolder> streamOfLines = Cell.getStreamOfLines(inputStream);

        String actualValue = streamOfLines.map(CellHolder::toString).collect(Collectors.joining(""));

        assertEquals(actualValue,expectedValue);

    }

    @DataProvider
    public Object[][] getTestGetCellHolderData() {
        return new Object[][] {
                {new int[][] {{0,1,0,1,1},{1,1,1,0,0},{0,0,1,0,0},{0,0,0,0,0},{1,1,1,1,1}},
                            "<[0 1 0 1 1]-[1 1 1 0 0]-[0 0 1 0 0]-[0 0 0 0 0]-[1 1 1 1 1]>"},
                {new int[][] {{0,1,0},{1,1,1},{0,0,1}},
                            "<[0 1 0]-[1 1 1]-[0 0 1]>"},
                {new int[][] {{},{1,1,1},{0}},
                               "<[1 1 1]-[0]>"},
        };
    }

    @Test(dataProvider = "getTestGetCellHolderData")
    public void testGetCellHolder(int[][] array, String expectedValue) {

        Stream<IntStream> inputStream = Arrays.stream(array).map(Arrays::stream);
        Stream<CellHolder> streamOfLines = Cell.getStreamOfLines(inputStream);

        CellHolder cellHolder = Cell.getCellHolder(streamOfLines);

        String actualValue = cellHolder.cell.toString();

        assertEquals(actualValue,expectedValue);

    }

    @DataProvider
    public Object[][] getTestValueOfData() {
        return new Object[][] {
                {new int[][] {{0,1,0,1,1},{1,1,1,0,0},{0,0,1,0,0},{0,0,0,0,0},{1,1,1,1,1}},
                        "<[0 1 0 1 1]-[1 1 1 0 0]-[0 0 1 0 0]-[0 0 0 0 0]-[1 1 1 1 1]>"},
                {new int[][] {{0,1,0},{1,1,1},{0,0,1}},
                        "<[0 1 0]-[1 1 1]-[0 0 1]>"},
                {new int[][] {{},{1,1,1},{0}},
                        "<[1 1 1]-[0]>"},
        };
    }

    @Test(dataProvider = "getTestValueOfData")
    public void testValueOf(int[][] array, String expectedValue) {

        Stream<IntStream> inputStream = Arrays.stream(array).map(Arrays::stream);
        Cell cell = Cell.valueOf(inputStream);
        String actualValue = cell.toString();
        assertEquals(actualValue,expectedValue);

    }

    @Test
    public void testGetNextGeneration() {

        Cell current = Cell.valueOf(CURRENT_GENERATION_STREAM);
        Stream<IntStream> nextGeneration = current.getNextGeneration();
        Cell actualNextGeneration = Cell.valueOf(nextGeneration);
        String actualValue = actualNextGeneration.toString();
        System.out.println(actualValue);

        Cell expectedNextGeneration = Cell.valueOf(NEXT_GENERATION_STREAM);
        String expectedValue = expectedNextGeneration.toString();
        System.out.println(expectedValue);

        assertEquals(actualValue,expectedValue);

    }

}
