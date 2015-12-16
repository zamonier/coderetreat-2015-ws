package ru.mercuriev.game.of.life

import org.springframework.stereotype.Service
import ru.mercuriev.game.of.life.NeighboursCounter

import java.util.stream.IntStream

/**
 * Created by Eugene on 17.12.15.
 */
@Service
class GEngine {
    public static final int ALIVE = 1;
    public static final int DEAD = 0;

    public int[][] next(int[][] world) {

        int size = world.length;
        int[][] result = new int[size][size];

        int[][] cellNeighborsAmount = NeighboursCounter.newInstance(world).countNeighbours();

        IntStream.range(0, size).each { i ->
            IntStream.range(0, size).each { j ->
                result[i][j] = nextState(world[i][j], cellNeighborsAmount[i][j])
            }
        }
        return result;
    }

    protected int nextState(int currentState, int neighboursCount) {
        if (currentState == ALIVE) {
            return (neighboursCount < 2 || neighboursCount > 3) ? DEAD : ALIVE;
        }
        if (currentState == DEAD && neighboursCount == 3) {
            return ALIVE;
        }
        return currentState;
    }

}
