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
                //{new int[] {0,1,0,0}, new int[] {0,1,0,1},"<[0 1 0 0]-[0 1 0 1]>"},
                //{new int[] {}, new int[] {},"<>"},
                {new int[] {0,1,0,0}, new int[] {},"<>"},
        };
    }

    // TODO separate method for merging multiline
    // TODO separate method with null

    @SuppressWarnings("SuspiciousNameCombination")
    @Test(dataProvider = "getTestMergeVerticalData")
    public void testMergeVertical(int[] statesTop, int[] statesBottom, String expectedAsString) {

        CellHolder top = constructLine(statesTop);
        CellHolder bottom = constructLine(statesBottom);

        CellHolder result = CellHolder.mergeVertical(top, bottom);

        // check merging
        Cell topCurrent = top.cell;
        Cell bottomCurrent = bottom.cell;
        if (topCurrent != null && bottomCurrent != null) {
            while (topCurrent.left != null) {
                assertTrue(topCurrent.bottom == bottomCurrent);
                assertTrue(bottomCurrent.top == topCurrent);
                topCurrent = topCurrent.left;
                bottomCurrent = bottomCurrent.left;
            }
        }

        // checking whole result
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
        assertEquals(actualAsString,expectedAsString);

    }


}

