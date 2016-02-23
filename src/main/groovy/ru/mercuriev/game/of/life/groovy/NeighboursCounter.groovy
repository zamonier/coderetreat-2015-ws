package ru.mercuriev.game.of.life.groovy

import static java.lang.Math.max
import static java.lang.Math.min

class NeighboursCounter {

    int[][] world

    public NeighboursCounter(int[][] world) {
        this.world = world
    }

    public int cellNeighborsAmount(int i, int j) {

        int neighboursCount = 0
        def size = world.length

        def left = max(0, i - 1)
        def right = min(i + 1, size - 1)
        def top = max(0, j - 1)
        def bottom = min(j + 1, size - 1)

        (left..right).each { x ->
            (top..bottom).each { y ->
                neighboursCount += world[x][y]
            }
        }

        return neighboursCount - world[i][j]
    }

}
