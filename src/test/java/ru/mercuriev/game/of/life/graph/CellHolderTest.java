package ru.mercuriev.game.of.life.graph;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.stream.Collectors;

import static org.testng.Assert.*;
import static ru.mercuriev.game.of.life.graph.CellTestUtils.*;

/**
 * @author paul
 */
public class CellHolderTest {

    @DataProvider
    public Object[][] getTestAppendData() {
        return new Object[][] {
                {construct(0,1,0,0),0,0,false,"01000"},
                {construct(0),1,1,false,"01"},
                {construct(),1,1,true,"1"},
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
                {construct(0,1,0,0),construct(1,0),"010010"},
                {construct(0,1,0,0),construct(),"0100"},
                {construct(),construct(0,1,0,0),"0100"},
                {construct(),construct(),"[empty]"},
        };
    }


    @Test(dataProvider = "getTestMergeHorizontalData")
    public void testMergeHorizontal(CellHolder left, CellHolder right, String expectedAsString) {

        CellHolder.mergeHorizontal(left,right);

        assertEquals(Cell.lineToStream(left.cell, cell -> cell.state).mapToObj(value -> "" + value).collect(Collectors.joining(" ")),expectedAsString);
        assertEquals(Cell.lineToStream(right.cell, cell -> cell.state).mapToObj(value -> "" + value).collect(Collectors.joining(" ")),expectedAsString);
        // TODO uncomment!
//        assertEquals(left.toString(),expectedAsString);
//        assertEquals(right.toString(),expectedAsString);

    }


    @Test
    public void testMethod() {
        CellHolder cellHolder = new CellHolder();
        CellHolder.append(cellHolder,0);
        CellHolder.append(cellHolder,1);
        CellHolder.append(cellHolder,0);
        CellHolder.append(cellHolder,1);
        CellHolder.append(cellHolder,1);
        //String string = cellHolder.toString();
        System.out.println("TA DAAAAA!");
    }


}

