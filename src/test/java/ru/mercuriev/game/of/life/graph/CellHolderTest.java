package ru.mercuriev.game.of.life.graph;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static org.testng.Assert.*;
import static ru.mercuriev.game.of.life.graph.CellTestUtils.*;

/**
 * @author paul
 */
public class CellHolderTest {

    @DataProvider
    public Object[][] getTestAppendData() {
        return new Object[][] {
                {new int[] {0,1,0,0},0,0,false,"01000"},
                {new int[] {0},1,1,false,"01"},
                {new int[] {},1,1,true,"1"},
        };
    }

    @Test(dataProvider = "getTestAppendData")
    public void testAppend(int[] states,int value, int expectedState,boolean leftCellIsNull, String expectedStringRepresentation) {

        CellHolder cellHolder = constructLine(states);

        CellHolder.append(cellHolder,value);

        Cell current = cellHolder.cell;

        assertEquals(cellHolder.toString(),expectedStringRepresentation);
        assertEquals(current.state,expectedState);
        assertEquals(current.left == null,leftCellIsNull);
        assertNull(current.right);
        assertNull(current.top);
        assertNull(current.bottom);

    }

    @DataProvider
    public Object[][] getTestMergeHorizontalData() {
        return new Object[][] {
                {new int[] {0,1,0,0}, new int[] {1,0},"010010"},
                {new int[] {0,1,0,0}, new int[] {},"0100"},
                {new int[] {}, new int[] {0,1,0,0},"0100"},
                {new int[] {}, new int[] {},"[empty]"},
        };
    }


    @Test(dataProvider = "getTestMergeHorizontalData")
    public void testMergeHorizontal(int[] statesLeft, int[] statesRight, String expectedAsString) {

        CellHolder left = constructLine(statesLeft);
        CellHolder right = constructLine(statesRight);

        CellHolder.mergeHorizontal(left,right);

        assertEquals(left.toString(),expectedAsString);
        assertEquals(right.toString(),expectedAsString);

    }

    @DataProvider
    public Object[][] getTestMergeVerticalData() {
        return new Object[][] {
                {new int[] {0,1,0,0}, new int[] {0,1,0,1},"<[0 1 0 0]-[0 1 0 1]>"},
                {new int[] {}, new int[] {},null},
                {new int[] {0,1,0,0}, new int[] {}, "<[0 1 0 0]>"},
                {new int[] {}, new int[] {0,1,0,0}, "<[0 1 0 0]>"},
        };
    }

    // TODO separate method for merging multiline
    // TODO separate method with null

    // TODo SHOULD! be rewritten

    @SuppressWarnings("SuspiciousNameCombination")
    @Test(dataProvider = "getTestMergeVerticalData")
    public void testMergeVertical(int[] statesTop, int[] statesBottom, String expectedAsString) {

        CellHolder top = constructLine(statesTop);
        CellHolder bottom = constructLine(statesBottom);

        boolean allIsNull = false;
        if (top.cell == null && bottom.cell == null) {
            allIsNull = true;
        }

        boolean topIsNull = false;
        if (top.cell == null) {
            topIsNull = true;
        }

        boolean bottomIsNull = false;
        if (bottom.cell == null) {
            bottomIsNull = true;
        }

        CellHolder result = CellHolder.mergeVertical(top, bottom);

        if (allIsNull) {
            assertTrue(bottom == result);
            return;
        }

        if (topIsNull || bottomIsNull) {
            assertTrue(bottom == result);
        }

        if (!topIsNull && !bottomIsNull) {
            // check merging
            checkLinesAreMerged(top.cell, bottom.cell);
        }


        // checking whole result
        // TODO use toString
        // ---------------------------------------------------
        Cell current = result.cell;
        if (current != null) {
            while (current.left != null)
                current = current.left;
            while (current.top != null)
                current = current.top;
        }

        Stream<IntStream> stream = Cell.cellToStream(current, cell -> cell.state);

        String actualAsString =
                stream.map(intStream -> intStream.mapToObj(value -> "" + value)
                        .collect(Collectors.joining(" ", "[", "]")))
                        .collect(Collectors.joining("-", "<", ">"));

        System.out.println("actualAsString = " + actualAsString);
        // ---------------------------------------------------
        assertEquals(actualAsString,expectedAsString);

    }

    @Test
    public void testMergeVerticalCpmplex() {

        CellHolder first = constructLine(0,1,0,1,1);
        Cell firstCell = first.cell;
        CellHolder second = constructLine(0,0,0,0,0);
        Cell secondCell = second.cell;

        CellHolder third = constructLine(1,1,1,1,1);
        Cell thirdCell = third.cell;

        CellHolder fourth = constructLine(0,0,0,1,1);
        Cell fourthCell = fourth.cell;

        CellHolder fifth = constructLine(1,1,0,0,0);
        Cell fifthCell = fifth.cell;

        CellHolder.mergeVertical(first,second);
        CellTestUtils.checkLinesAreMerged(firstCell,secondCell);

        CellHolder.mergeVertical(second,third);
        CellTestUtils.checkLinesAreMerged(secondCell,thirdCell);

        CellHolder.mergeVertical(fourth,fifth);
        CellTestUtils.checkLinesAreMerged(fourthCell,fifthCell);

        CellHolder.mergeVertical(third,fifth);
        CellTestUtils.checkLinesAreMerged(thirdCell,fourthCell);

        // TODO use toString
        // ---------------------------------------------------
        Cell current = fourthCell;
        if (current != null) {
            while (current.left != null)
                current = current.left;
            while (current.top != null)
                current = current.top;
        }

        Stream<IntStream> stream = Cell.cellToStream(current, cell -> cell.state);

        // TODO toString
        String actualAsString =
                stream.map(intStream -> intStream.mapToObj(value -> "" + value)
                        .collect(Collectors.joining(" ", "[", "]")))
                        .collect(Collectors.joining("-", "<", ">"));

        System.out.println("actualAsString = " + actualAsString);
        // ---------------------------------------------------
        assertEquals(actualAsString,"<[0 1 0 1 1]-[0 0 0 0 0]-[1 1 1 1 1]-[0 0 0 1 1]-[1 1 0 0 0]>");



    }


}

