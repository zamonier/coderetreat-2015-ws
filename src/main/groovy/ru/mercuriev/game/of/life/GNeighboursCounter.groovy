package ru.mercuriev.game.of.life

import java.util.stream.IntStream

/**
 * Created by Eugene on 17.12.15.
 */
class GNeighboursCounter {

    int[][] world

    public static GNeighboursCounter newInstance(int[][] world) {
        return new GNeighboursCounter(world)
    }

    private GNeighboursCounter(int[][] world) {
        this.world = world
    }

    public int[][] countNeighbours() {

        int size = world.length
        int[][] result = new int[size][size]

        IntStream.range(0, size).each { i ->
            IntStream.range(0, size).each { j ->
                result[i][j] = this.cellNeighborsAmount(i, j)
            }
        }
        return result
    }

    private int cellNeighborsAmount(int i, int j) {
        int neighboursCount = 0
        //println screen*.getAt( 2 )
        (Math.max(0, i - 1)..i + 1).each { x ->
            (j - 1..j + 1).each { y ->
                if (!isOutOfBorder(x, y) && (x != i || y != j)) {
                    neighboursCount += world[x][y]
                }
            }
        }
        return neighboursCount
    }
    // checking borders to avoid out of range exception
    private boolean isOutOfBorder(int x, int y) {
        return x < 0 || x >= world.length || y < 0 || y >= world.length
    }

}
