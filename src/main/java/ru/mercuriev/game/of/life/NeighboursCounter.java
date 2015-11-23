package ru.mercuriev.game.of.life;

import java.util.stream.IntStream;

/**
 * @author paul
 * @author Eugene
 */
class NeighboursCounter {

    private int[][] world;

    public static NeighboursCounter newInstance(int[][] world) {
        return new NeighboursCounter(world);
    }

    private NeighboursCounter(int[][] world) {
        this.world = world;
    }

    public int[][] countNeighbours() {

        int size = world.length;
        int[][] result = new int[size][size];

        IntStream.range(0, size).forEach(i ->
                IntStream.range(0, size).forEach(j ->
                        result[i][j] = this.cellNeighborsAmount(i, j)
                )
        );
        return result;
    }

    private int cellNeighborsAmount(int i, int j) {
        int neighboursCount = 0;
        for (int x = i - 1; x <= i + 1; x++) {
            for (int y = j - 1; y <= j + 1; y++) {
                if (isOutOfBorder(x, y)) {
                    continue;
                }
                // with no impact of the cell itself
                if (x == i && y == j) {
                    continue;
                }
                neighboursCount += world[x][y];
            }
        }
        return neighboursCount;
    }

    // checking borders to avoid out of range exception
    private boolean isOutOfBorder(int x, int y) {
        return x < 0 || x >= world.length || y < 0 || y >= world.length;
    }
}
