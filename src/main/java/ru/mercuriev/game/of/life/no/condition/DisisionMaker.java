package ru.mercuriev.game.of.life.no.condition;

/**
 * Created by Eugene on 24.11.15.
 */
public class DisisionMaker {


    public static Cell nextGenCell(Cell c, int neighboursAmount) {
        return new AliveCell(c.getX(), c.getY());
    }
}
