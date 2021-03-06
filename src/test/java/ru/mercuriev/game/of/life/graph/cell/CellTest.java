package ru.mercuriev.game.of.life.graph.cell;


import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;
import static ru.mercuriev.game.of.life.graph.cell.CellTestUtils.*;

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

        Cell current = new Cell(currentState);
        int actualNextState = current.calculateNextState(countOfLiveNeighbours);
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

        CellBuilder cellBuilder = constructLine(array);
        Cell current = cellBuilder.build().orElse(null);

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

        CellBuilder cellBuilder = constructLine(array);
        Cell current = cellBuilder.build().orElse(null);

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

    @SuppressWarnings("SuspiciousNameCombination")
    @Test(dataProvider = "getTestGetNextLineCellMultiLineData")
    public void testGetNextLineCellMultiLine(int[] array) {

        CellBuilder cellBuilder = constructLine(array);
        Cell expectedLeft = cellBuilder.build().orElseGet(() -> {throw new AssertionError("Builder is empty");});

        Cell current = expectedLeft;
        while (current.right != null)
            current = current.right;

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
        };
    }

    @Test(dataProvider = "getTestCellToStreamData")
    public void testCellToStream(int[][] values, String expectedValue) {

        Cell current = constructMultiline(values);

        Stream<IntStream> stream = current.cellToStream(cell -> cell.state);

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

        int actualCount = central.getCountOfNeighboursAlive();
        assertEquals(actualCount, expectedCount);

    }

    @DataProvider
    public Object[][] getTestBuildLinesData() {
        return new Object[][] {
                {new int[][] {{0,1,0,1,1}, {1,1,1,0,0}, {0,0,1,0,0}, {0,0,0,0,0}, {1,1,1,1,1}},
                            "<[0 1 0 1 1]><[1 1 1 0 0]><[0 0 1 0 0]><[0 0 0 0 0]><[1 1 1 1 1]>"},
                {new int[][] {{0,1,0}, {1,1,1}, {0,0,1}},
                            "<[0 1 0]><[1 1 1]><[0 0 1]>"},
                {new int[][] {{},{1,1,1}, {0}},
                      "<[empty]><[1 1 1]><[0]>"},
        };
    }

    @Test(dataProvider = "getTestBuildLinesData")
    public void testBuildLines(int[][] array, String expectedValue) {

        Stream<IntStream> inputStream = Arrays.stream(array).map(Arrays::stream);

        Stream<CellBuilder> streamOfLines = Cell.buildLines(inputStream);

        String actualValue = streamOfLines.map(CellBuilder::toString).collect(Collectors.joining(""));

        assertEquals(actualValue,expectedValue);

    }

    @DataProvider
    public Object[][] getTestBuildGraphData() {
        return new Object[][] {
                {new int[][] {{0,1,0,1,1},{1,1,1,0,0},{0,0,1,0,0},{0,0,0,0,0},{1,1,1,1,1}},
                            "<[0 1 0 1 1]-[1 1 1 0 0]-[0 0 1 0 0]-[0 0 0 0 0]-[1 1 1 1 1]>"},
                {new int[][] {{0,1,0},{1,1,1},{0,0,1}},
                            "<[0 1 0]-[1 1 1]-[0 0 1]>"},
                {new int[][] {{},{1,1,1},{0}},
                               "<[1 1 1]-[0]>"},
        };
    }

    @Test(dataProvider = "getTestBuildGraphData")
    public void testBuildGraph(int[][] array, String expectedValue) {

        Stream<IntStream> inputStream = Arrays.stream(array).map(Arrays::stream);
        Stream<CellBuilder> streamOfLines = Cell.buildLines(inputStream);

        CellBuilder cellBuilder = Cell.buildGraph(streamOfLines);

        String actualValue = cellBuilder.build()
                                        .orElseGet(() -> {throw new AssertionError("Builder is empty");})
                                        .toString();

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

        Cell expectedNextGeneration = Cell.valueOf(NEXT_GENERATION_STREAM);
        String expectedValue = expectedNextGeneration.toString();

        assertEquals(actualValue,expectedValue);

    }

}
