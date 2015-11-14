package ru.mercuriev;

/**
 * Created by Eugene on 15.11.15.
 */
public class Counter {
    private int[][] world;

    public Counter(int[][] world) {
        this.world = world;
    }

    public int countNeighbours(int i, int j) {

        int neighbours = 0;
        for (int x = i - 1; x <= i + 1; x++) {
            for (int y = j - 1; y <= j + 1; y++) {
                if (x < 0 || x >= world.length || y < 0 || y >= world.length) {
                    continue;
                }
                neighbours += world[x][y];
            }
        }
        return neighbours - world[i][j]; //  don't count cell itself
    }

}

