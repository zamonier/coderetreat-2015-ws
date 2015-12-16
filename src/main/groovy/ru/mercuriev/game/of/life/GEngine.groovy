package ru.mercuriev.game.of.life

import org.springframework.stereotype.Service

@Service
class GEngine {
    public static final int ALIVE = 1
    public static final int DEAD = 0

    public int[][] next(int[][] world) {

        int size = world.length
        def result = new int[size][size]

        def cellNeighborsAmount = NeighboursCounter.newInstance(world).countNeighbours()

        world.eachWithIndex { int[] line, int i ->
            line.eachWithIndex { int cell, int j ->
                result[i][j] = nextState(cell, cellNeighborsAmount[i][j])
            }
        }
        result
    }

    protected int nextState(int currentState, int neighboursCount) {
        if (currentState) {
            return neighboursCount == 2 || neighboursCount == 3 ? ALIVE : DEAD
        }
        if (!currentState && neighboursCount == 3) {
            return ALIVE
        }
        currentState
    }

}
