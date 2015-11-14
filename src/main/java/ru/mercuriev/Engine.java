package ru.mercuriev;

import org.springframework.stereotype.Service;

@Service
public class Engine {

    public static final int ALIVE = 1;
    public static final int DEAD = 0;

    public int[][] nextGeneration(int[][] world) {

        int size = world.length;
        int[][] result = new int[size][size];
        Counter counter = new Counter(world);

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                int neighbours = counter.countNeighbours(i, j);
                result[i][j] = stateInNextGeneration(world[i][j], neighbours);
            }
        }
        return result;
    }

    protected int stateInNextGeneration(int currentState, int neighbours) {
        if (currentState == ALIVE) {
            return (neighbours < 2 || neighbours > 3) ? DEAD : ALIVE;
        }
        if (currentState == DEAD && neighbours == 3) {
            return ALIVE;
        }
        return currentState;
    }
}