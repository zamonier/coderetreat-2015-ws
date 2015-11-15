package ru.mercuriev;

import org.springframework.stereotype.Service;

@Service
public class Engine {

    public static final int ALIVE = 1;
    public static final int DEAD = 0;

    public int[][] nextGeneration(int[][] world) {

        int size = world.length;
        int[][] result = new int[size][size];
        Counter counter = Counter.of(world);

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                int neighbours = counter.countNeighbours(i, j);
                result[i][j] = nextState(world[i][j], neighbours);
            }
        }
        return result;
    }

    protected int nextState(int currentState, int neighbours) {
        if (currentState == ALIVE) {
            return (neighbours < 2 || neighbours > 3) ? DEAD : ALIVE;
        }
        if (currentState == DEAD && neighbours == 3) {
            return ALIVE;
        }
        return currentState;
    }

    public static class Counter {

        private int[][] world;

        public static Counter of(int[][] world) {
            return new Counter(world);
        }

        private Counter(int[][] world) {
            this.world = world;
        }

        public int countNeighbours(int i, int j) {
            int neighbours = 0;
            for (int x = i - 1; x <= i + 1; x++) {
                for (int y = j - 1; y <= j + 1; y++) {
                    if (x < 0 || x >= world.length || y < 0 || y >= world.length) continue;
                    if (x == i && y == j) continue;
                    neighbours += world[x][y];
                }
            }
            return neighbours;
        }

    }
}