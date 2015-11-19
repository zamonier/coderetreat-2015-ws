package ru.mercuriev.game.of.life;

/**
 * @author paul
 */
class NeighboursCounter {

    private int[][] world;

    public static NeighboursCounter newInstance(int[][] world) {
        return new NeighboursCounter(world);
    }

    private NeighboursCounter(int[][] world) {
        this.world = world;
    }

    public int countNeighbours(int i, int j) {
        int neighboursCount = 0;
        for (int x = i - 1; x <= i + 1; x++) {
            for (int y = j - 1; y <= j + 1; y++) {
                if (isOutOfRange(x, y)) {
                    continue;
                }
                if (isItself(i, j, x, y)) {
                    continue;
                }
                neighboursCount += world[x][y];
            }
        }
        return neighboursCount;
    }

    // with no impact of the cell itself
    private boolean isItself(int i, int j, int x, int y) {
        return x == i && y == j;
    }

    // checking borders to avoid out of range exception
    private boolean isOutOfRange(int x, int y) {
        return x < 0 || x >= world.length || y < 0 || y >= world.length;
    }

}
