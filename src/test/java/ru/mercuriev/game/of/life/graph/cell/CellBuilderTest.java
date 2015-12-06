package ru.mercuriev.game.of.life.graph.cell;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

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
        cellBuilder.append(newState);

        Cell current = cellBuilder.build().orElseGet(() -> {throw new AssertionError("Builder is empty");});
        while (current.right != null)
            current = current.right;

        assertEquals(cellBuilder.build().get().toString(),expectedResult);
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

        left.mergeHorizontal(right);

        assertEquals(left.toString(),expectedAsString);
        assertNull(right.build().orElse(null));

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

    @SuppressWarnings("SuspiciousNameCombination")
    @Test(dataProvider = "getTestMergeVerticalData")
    public void testMergeVertical(int[] statesTop, int[] statesBottom, String expectedAsString) {

        CellBuilder top = constructLine(statesTop);
        CellBuilder bottom = constructLine(statesBottom);

        boolean allIsNull = false;
        if (!top.build().isPresent() && !bottom.build().isPresent()) {
            allIsNull = true;
        }

        boolean topIsNull = false;
        if (!top.build().isPresent()) {
            topIsNull = true;
        }

        boolean bottomIsNull = false;
        if (!bottom.build().isPresent()) {
            bottomIsNull = true;
        }

        Cell topLine = top.build().orElse(null);
        Cell bottomLine = bottom.build().orElse(null);

        CellBuilder result = top.mergeVertical(bottom);

        if (topIsNull || bottomIsNull) {
            assertTrue(top == result);
            assertNull(bottom.build().orElse(null));
        }

        if (allIsNull) {
            return;
        }

        if (!topIsNull && !bottomIsNull) {
            // check merging
            checkLinesAreMerged(topLine, bottomLine);
        }

        String actualAsString = result.build().get().toString();
        assertEquals(actualAsString,expectedAsString);


    }

    @Test
    public void testMergeVerticalComplex() {

        CellBuilder first = constructLine(0,1,0,1,1);
        Cell firstCell = first.build().orElseGet(() -> {throw new AssertionError("Builder is empty");});
        CellBuilder second = constructLine(0,0,0,0,0);
        Cell secondCell = second.build().orElseGet(() -> {throw new AssertionError("Builder is empty");});

        CellBuilder third = constructLine(1,1,1,1,1);
        Cell thirdCell = third.build().orElseGet(() -> {throw new AssertionError("Builder is empty");});

        CellBuilder fourth = constructLine(0,0,0,1,1);
        Cell fourthCell = fourth.build().orElseGet(() -> {throw new AssertionError("Builder is empty");});

        CellBuilder fifth = constructLine(1,1,0,0,0);
        Cell fifthCell = fifth.build().orElseGet(() -> {throw new AssertionError("Builder is empty");});

        first.mergeVertical(second);
        CellTestUtils.checkLinesAreMerged(firstCell,secondCell);

        first.mergeVertical(third);
        CellTestUtils.checkLinesAreMerged(secondCell,thirdCell);

        fourth.mergeVertical(fifth);
        CellTestUtils.checkLinesAreMerged(fourthCell,fifthCell);

        first.mergeVertical(fourth);
        CellTestUtils.checkLinesAreMerged(thirdCell,fourthCell);

        String actualAsString = fourthCell.toString();
        assertEquals(actualAsString,"<[0 1 0 1 1]-[0 0 0 0 0]-[1 1 1 1 1]-[0 0 0 1 1]-[1 1 0 0 0]>");



    }


}

