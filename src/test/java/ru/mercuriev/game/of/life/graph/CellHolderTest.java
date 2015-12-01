package ru.mercuriev.game.of.life.graph;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.*;
import static ru.mercuriev.game.of.life.graph.CellTestUtils.*;

/**
 * @author paul
 */
public class CellHolderTest {

    @DataProvider
    public Object[][] getTestAppendData() {
        return new Object[][] {
                {constructLine(0,1,0,0),0,0,false,"01000"},
                {constructLine(0),1,1,false,"01"},
                {constructLine(),1,1,true,"1"},
        };
    }

    @Test(dataProvider = "getTestAppendData")
    public void testAppend(CellHolder cellHolder,int value, int expectedState,boolean leftCellIsNull, String expectedStringRepresentation) {

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
                {constructLine(0,1,0,0), constructLine(1,0),"010010"},
                {constructLine(0,1,0,0), constructLine(),"0100"},
                {constructLine(), constructLine(0,1,0,0),"0100"},
                {constructLine(), constructLine(),"[empty]"},
        };
    }


    @Test(dataProvider = "getTestMergeHorizontalData")
    public void testMergeHorizontal(CellHolder left, CellHolder right, String expectedAsString) {

        CellHolder.mergeHorizontal(left,right);

        assertEquals(left.toString(),expectedAsString);
        assertEquals(right.toString(),expectedAsString);

    }


}

