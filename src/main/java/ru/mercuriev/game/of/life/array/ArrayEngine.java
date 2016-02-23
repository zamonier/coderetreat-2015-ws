package ru.mercuriev.game.of.life.array;

import org.springframework.stereotype.Service;
import ru.mercuriev.game.of.life.Engine;

import java.util.stream.IntStream;

@Service
public class ArrayEngine implements Engine {

    @Override
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