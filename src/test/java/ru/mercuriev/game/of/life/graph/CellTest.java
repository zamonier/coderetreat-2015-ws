package ru.mercuriev.game.of.life.graph;


import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.stream.Stream;

/**
 * @author paul
 */
public class CellTest {

    private int[][] CURRENT_GENERATION_INT = {
            {0,0,0,0},
            {0,0,0,0},
            {0,0,0,0},
            {0,0,0,0},
    };

    @Test
    public void testDummy() {
        System.out.println("TA DAAAAA!");
    }

//    @DataProvider
//    public Object[][] getDestCalculateNextStateData() {
//        return new Object[][] {
//                {Cell.ALIVE, 0, Cell.DEAD},
//                {Cell.ALIVE, 1, Cell.DEAD},
//                {Cell.ALIVE, 2, Cell.ALIVE},
//                {Cell.ALIVE, 3, Cell.ALIVE},
//                {Cell.ALIVE, 4, Cell.DEAD},
//                {Cell.ALIVE, 5, Cell.DEAD},
//                {Cell.ALIVE, 6, Cell.DEAD},
//                {Cell.ALIVE, 7, Cell.DEAD},
//                {Cell.ALIVE, 8, Cell.DEAD},
//                {Cell.DEAD,  0, Cell.DEAD},
//                {Cell.DEAD,  1, Cell.DEAD},
//                {Cell.DEAD,  2, Cell.DEAD},
//                {Cell.DEAD,  3, Cell.ALIVE},
//                {Cell.DEAD,  4, Cell.DEAD},
//                {Cell.DEAD,  5, Cell.DEAD},
//                {Cell.DEAD,  6, Cell.DEAD},
//                {Cell.DEAD,  7, Cell.DEAD},
//                {Cell.DEAD,  8, Cell.DEAD},
//        };
//    }
//
//    @Test(dataProvider = "getDestCalculateNextStateData")
//    public void testCalculateNextState(int currentState, int countOfLiveNeighbours, int expectedNextState) {
//
//        int actualNextState = new Cell(currentState).calculateNextState(countOfLiveNeighbours);
//        Assert.assertEquals(actualNextState,expectedNextState);
//
//    }

}
