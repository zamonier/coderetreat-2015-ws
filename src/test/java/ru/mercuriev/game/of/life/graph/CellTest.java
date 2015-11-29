package ru.mercuriev.game.of.life.graph;


import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * @author paul
 */
public class CellTest {

    private int[][] CURRENT_GENERATION = {
            {0,0,0,0,0},
            {0,0,0,1,0},
            {0,1,0,1,0},
            {0,0,1,1,0},
            {0,0,0,0,0},
    };
    private Stream<IntStream> CURRENT_GENERATION_STREAM = Arrays.stream(CURRENT_GENERATION).map(Arrays::stream);

    private int[][] NEXT_GENERATION = {
            {0,0,0,0,0},
            {0,0,1,0,0},
            {0,0,0,1,1},
            {0,0,1,1,0},
            {0,0,0,0,0},
    };
    private Stream<IntStream> NEXT_GENERATION_STREAM = Arrays.stream(NEXT_GENERATION).map(Arrays::stream);

//    @Test
//    public void testLineToStream() {
//        Cell cell = Cell.valueOf(CURRENT_GENERATION_STREAM);
//
//        int counter = 0;
//        while (counter <= 3) {
//            cell = cell.bottom;
//        }
//
//        IntStream stream = Cell.lineToStream(cell, cell -> cell.state);
//        String actualResult = stream.collect(StringBuilder::new, StringBuilder::append, StringBuilder::append).toString();
//        Assert.assertEquals(actualResult,"00110");
//
//    }

    @DataProvider
    public Object[][] getCalculateNextStateData() {
        return new Object[][] {
                {Cell.ALIVE, 0, Cell.DEAD},
                {Cell.ALIVE, 1, Cell.DEAD},
                {Cell.ALIVE, 2, Cell.ALIVE},
                {Cell.ALIVE, 3, Cell.ALIVE},
                {Cell.ALIVE, 4, Cell.DEAD},
                {Cell.ALIVE, 5, Cell.DEAD},
                {Cell.ALIVE, 6, Cell.DEAD},
                {Cell.ALIVE, 7, Cell.DEAD},
                {Cell.ALIVE, 8, Cell.DEAD},
                {Cell.DEAD,  0, Cell.DEAD},
                {Cell.DEAD,  1, Cell.DEAD},
                {Cell.DEAD,  2, Cell.DEAD},
                {Cell.DEAD,  3, Cell.ALIVE},
                {Cell.DEAD,  4, Cell.DEAD},
                {Cell.DEAD,  5, Cell.DEAD},
                {Cell.DEAD,  6, Cell.DEAD},
                {Cell.DEAD,  7, Cell.DEAD},
                {Cell.DEAD,  8, Cell.DEAD},
        };
    }

    @Test(dataProvider = "getCalculateNextStateData")
    public void testCalculateNextState(int currentState, int countOfLiveNeighbours, int expectedNextState) {

        Cell current = new Cell();
        current.state = currentState;
        int actualNextState = Cell.calculateNextState(current,countOfLiveNeighbours);
        Assert.assertEquals(actualNextState,expectedNextState);

    }

}
