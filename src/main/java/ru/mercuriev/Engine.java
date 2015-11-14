package ru.mercuriev;

import org.springframework.stereotype.Service;

@Service
public class Engine {

    public int[][] nextGeneration(int[][] world) {
        int[][] result = new int[world.length][world.length];

        for (int i = 0; i < world.length; i++) {
            for (int j = 0; j < world.length; j++) {
                result[i][j] = isAlive(world, i, j);
            }
        }

        return result;
    }

    protected int isAlive(int[][] world, int i, int j) {
        int counter = 0;
        for (int x = i - 1; x <= i + 1; x++) {
            for (int y = j - 1; y <= j + 1; y++) {
                if (x >= 0 && x < world.length)
                    if (y >= 0 && y < world.length)
                        if (!(x == i && y == j))
                            counter += world[x][y];
            }
        }
        if (world[i][j] == 1) {
            return (counter == 2 || counter == 3) ? 1 : 0;
        }
        if (world[i][j] == 0 && counter == 3) {
            return 1;
        }
        return world[i][j];
    }
}