package ru.mercuriev.game.of.life.graph.cell;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static org.testng.Assert.*;
import static ru.mercuriev.game.of.life.graph.cell.CellTestUtils.*;

/**
 * @author paul
 */
public class CellBuilderTest {

    @DataProvider
    public Object[][] getTestAppendData() {
        return new Object[][] {
                {new int[] {0,1,0,0},0,0,false,"<[0 1 0 0 0]>"},
                {new int[] {0},1,1,false,"<[0 1]>"},
                {new int[] {},1,1,true,"<[1]>"},
        };
    }

    @Test(dataProvider = "getTestAppendData")
    public void testAppend(int[] states, int newState, int expectedState, boolean leftCellIsNull, String expectedResult) {

        CellBuilder cellBuilder = constructLine(states);
        CellBuilder.append(cellBuilder,newState);

        Cell current = cellBuilder.build();
        while (current.right != null)
            current = current.right;

        assertEquals(cellBuilder.build().toString(),expectedResult);
        assertEquals(current.state,expectedState);
        assertEquals(current.left == null,leftCellIsNull);
        assertNull(current.right);
        assertNull(current.top);
        assertNull(current.bottom);

    }

    @DataProvider
    public Object[][] getTestMergeHorizontalData() {
        return new Object[][] {
                {new int[] {0,1,0,0}, new int[] {1,0},"<[0 1 0 0 1 0]>"},
                {new int[] {0,1,0,0}, new int[] {},"<[0 1 0 0]>"},
                {new int[] {}, new int[] {0,1,0,0},"<[0 1 0 0]>"},
                {new int[] {}, new int[] {},"<[empty]>"},
        };
    }


    @Test(dataProvider = "getTestMergeHorizontalData")
    public void testMergeHorizontal(int[] statesLeft, int[] statesRight, String expectedAsString) {

        CellBuilder left = constructLine(statesLeft);
        CellBuilder right = constructLine(statesRight);

        CellBuilder.mergeHorizontal(left,right);

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

    // TODo SHOULD! be rewritten

    @SuppressWarnings("SuspiciousNameCombination")
    @Test(dataProvider = "getTestMergeVerticalData")
    public void testMergeVertical(int[] statesTop, int[] statesBottom, String expectedAsString) {

        CellBuilder top = constructLine(statesTop);
        CellBuilder bottom = constructLine(statesBottom);

        boolean allIsNull = false;
        if (top.build() == null && bottom.build() == null) {
            allIsNull = true;
        }

        boolean topIsNull = false;
        if (top.build() == null) {
            topIsNull = true;
        }

        boolean bottomIsNull = false;
        if (bottom.build() == null) {
            bottomIsNull = true;
        }

        Cell topLine = top.build();
        Cell botoomLine = bottom.build();

        CellBuilder result = CellBuilder.mergeVertical(top, bottom);

        if (allIsNull) {
            assertTrue(bottom == result);
            return;
        }

        if (topIsNull || bottomIsNull) {
            assertTrue(bottom == result);
        }

        if (!topIsNull && !bottomIsNull) {
            // check merging
            checkLinesAreMerged(topLine, botoomLine);
        }

        String actualAsString = result.build().toString();
        assertEquals(actualAsString,expectedAsString);

    }

    @Test
    public void testMergeVerticalComplex() {

        CellBuilder first = constructLine(0,1,0,1,1);
        Cell firstCell = first.build();
        CellBuilder second = constructLine(0,0,0,0,0);
        Cell secondCell = second.build();

        CellBuilder third = constructLine(1,1,1,1,1);
        Cell thirdCell = third.build();

        CellBuilder fourth = constructLine(0,0,0,1,1);
        Cell fourthCell = fourth.build();

        CellBuilder fifth = constructLine(1,1,0,0,0);
        Cell fifthCell = fifth.build();

        CellBuilder.mergeVertical(first,second);
        CellTestUtils.checkLinesAreMerged(firstCell,secondCell);

        CellBuilder.mergeVertical(second,third);
        CellTestUtils.checkLinesAreMerged(secondCell,thirdCell);

        CellBuilder.mergeVertical(fourth,fifth);
        CellTestUtils.checkLinesAreMerged(fourthCell,fifthCell);

        CellBuilder.mergeVertical(third,fifth);
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

        Stream<IntStream> stream = current.toStream();

        // TODO toString
        String actualAsString =
                stream.map(intStream -> intStream.mapToObj(value -> "" + value)
                        .collect(Collectors.joining(" ", "[", "]")))
                        .collect(Collectors.joining("-", "<", ">"));

        // ---------------------------------------------------
        assertEquals(actualAsString,"<[0 1 0 1 1]-[0 0 0 0 0]-[1 1 1 1 1]-[0 0 0 1 1]-[1 1 0 0 0]>");



    }


}

