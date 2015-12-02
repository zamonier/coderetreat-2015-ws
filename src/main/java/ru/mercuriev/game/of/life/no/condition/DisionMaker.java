package ru.mercuriev.game.of.life.no.condition;

import ru.mercuriev.game.of.life.no.condition.cells.AliveCell;
import ru.mercuriev.game.of.life.no.condition.cells.Cell;
import ru.mercuriev.game.of.life.no.condition.cells.DeadCell;

public class DisionMaker {

    private static Class<Cell>[] NEXT_STATES_FOR_ALIVE = new Class[]{
            DeadCell.class, DeadCell.class, AliveCell.class,
            AliveCell.class, DeadCell.class, DeadCell.class,
            DeadCell.class, DeadCell.class, DeadCell.class};
    private static Class<Cell>[] NEXT_STATES_FOR_DEAD = new Class[]{
            DeadCell.class, DeadCell.class, DeadCell.class,
            AliveCell.class, DeadCell.class, DeadCell.class,
            DeadCell.class, DeadCell.class, DeadCell.class};
    private static Class<Cell>[][] NEXT_STATES = new Class[][]{NEXT_STATES_FOR_DEAD, NEXT_STATES_FOR_ALIVE};

    public static Cell nextGenCell(Cell c, int neighboursAmount) {
        try {
            Cell cell = NEXT_STATES[c.getState()][neighboursAmount].newInstance();
            cell.setX(c.getX());
            cell.setY(c.getY());
            return cell;
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }

}
