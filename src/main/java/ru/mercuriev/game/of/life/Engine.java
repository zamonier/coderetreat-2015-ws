package ru.mercuriev.game.of.life;

import org.springframework.stereotype.Service;

import java.util.stream.IntStream;

// TODO Should be renamed to ArrayEngine and moved to specific package
// TODO extract interface
@Service
public class Engine {

    public static final int ALIVE = 1;
    public static final int DEAD = 0;

    public int[][] next(int[][] world) {

        int size = world.length;
        int[][] result = new int[size][size];

        int[][] cellNeighborsAmount = NeighboursCounter.newInstance(world).countNeighbours();

        IntStream.range(0, size).forEach(i ->
            IntStream.range(0, size).forEach(j ->
                    result[i][j] = nextState(world[i][j], cellNeighborsAmount[i][j])
            )
        );
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