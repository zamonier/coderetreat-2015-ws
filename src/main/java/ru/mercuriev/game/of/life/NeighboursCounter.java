package ru.mercuriev.game.of.life;

/**
 * @author paul
 */
class NeighboursCounter {

    private int[][] world;

    public static NeighboursCounter getInstance(int[][] world) {
        return new NeighboursCounter(world);
    }

    private NeighboursCounter(int[][] world) {
        this.world = world;
    }

    public int countNeighbours(int i, int j) {
        int neighboursCount = 0;
        for (int x = i - 1; x <= i + 1; x++) {
            for (int y = j - 1; y <= j + 1; y++) {
                // checking borders to avoid out of range exception
                if (x < 0 || x >= world.length || y < 0 || y >= world.length) {
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

}
