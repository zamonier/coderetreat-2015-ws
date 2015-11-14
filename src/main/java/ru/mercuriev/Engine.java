package ru.mercuriev;

import org.springframework.stereotype.Service;

@Service
public class Engine {

    public static final int ALIVE = 1;
    public static final int DEAD = 0;

    public int[][] nextGeneration(int[][] world) {

        int size = world.length;
        int[][] result = new int[size][size];

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                result[i][j] = cellInNextGeneration(i, j, world);
            }
        }
        return result;
    }

    protected int cellInNextGeneration(int i, int j, int[][] world) {

        int cell = world[i][j];
        int neighbours = countNeighbours(i, j, world);

        if (cell == ALIVE) {
            return (neighbours < 2 || neighbours > 3) ? DEAD : ALIVE;
        }
        if (cell == DEAD && neighbours == 3) {
            return ALIVE;
        }
        return cell;
    }

    protected int countNeighbours(int i, int j, int[][] world) {

        int neighbours = 0;
        int size = world.length;

        for (int x = i - 1; x <= i + 1; x++) {
            for (int y = j - 1; y <= j + 1; y++) {
                if (x < 0 || x >= size || y < 0 || y >= size || (x == i && y == j)) {
                    continue;
                }
                neighbours += world[x][y];
            }
        }
        return neighbours;
    }
}