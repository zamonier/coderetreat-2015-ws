package ru.mercuriev.game.of.life.graph;

/**
 * Utility class for testing Cell & CellHolder
 *
 * @author paul
 */
public class CellTestUtils {

    static CellHolder construct(int ... values) {
        CellHolder cellHolder = new CellHolder();
        for (int value : values) {
            CellHolder.append(cellHolder,value);
        }
        return cellHolder;
    }

}
