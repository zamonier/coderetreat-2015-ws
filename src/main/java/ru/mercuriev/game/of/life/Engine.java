package ru.mercuriev.game.of.life;

public interface Engine {

    int ALIVE = 1;
    int DEAD = 0;

    int[][] next(int[][] world);
}
