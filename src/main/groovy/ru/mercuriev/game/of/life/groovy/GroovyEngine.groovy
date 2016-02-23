package ru.mercuriev.game.of.life.groovy

import org.springframework.stereotype.Service

@Service
public class GroovyEngine {

    public static final int ALIVE = 1
    public static final int DEAD = 0

    public int[][] next(int[][] world) {

        int size = world.length
        def result = new int[size][size]
        def counter = new NeighboursCounter(world)

        world.eachWithIndex { int[] line, int i ->
            line.eachWithIndex { int cell, int j ->
                def neighborsAmount = counter.cellNeighborsAmount(i, j)
                result[i][j] = nextState(cell, neighborsAmount)
            }
        }

        result
    }

    protected int nextState(int currentState, int neighboursCount) {
        if (currentState == ALIVE) {
            return neighboursCount == 2 || neighboursCount == 3 ? ALIVE : DEAD
        } else {
            return neighboursCount == 3 ? ALIVE : DEAD
        }
    }

}
