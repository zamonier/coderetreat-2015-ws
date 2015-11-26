package ru.mercuriev.game.of.life.no.condition;

import ru.mercuriev.game.of.life.no.condition.cells.AliveCell;
import ru.mercuriev.game.of.life.no.condition.cells.Cell;

/**
 * Created by Eugene on 24.11.15.
 */
public class DisisionMaker {


    public static Cell nextGenCell(Cell c, int neighboursAmount) {
        return new AliveCell(c.getX(), c.getY());
    }
}
