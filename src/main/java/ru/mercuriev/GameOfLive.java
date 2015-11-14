package ru.mercuriev;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by Eugene on 14.11.15.
 */
@Controller
public class GameOfLive {

    @RequestMapping("/next-gen")
    @ResponseBody
    public String nextGen(@RequestParam String gen) throws JsonProcessingException {
        return print(calculate(parse(gen)));
    }

    private String print(int[][] result) throws JsonProcessingException {
        return new ObjectMapper().writeValueAsString(result);
    }

    private int[][] parse(String gen) {
        String[] s = gen.split(",");
        int size = Integer.parseInt(s[0]);

        int[][] world = new int[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                world[i][j] = Integer.parseInt(s[i * size + j + 1]);
            }
        }
        return world;
    }

    private int[][] calculate(int[][] world) {
        int[][] result = new int[world.length][world.length];

        for (int i = 0; i < world.length; i++) {
            for (int j = 0; j < world.length; j++) {
                result[i][j] = isAlive(world, i, j);
            }
        }

        return result;
    }

    private int isAlive(int[][] world, int i, int j) {
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
            if (counter == 2 || counter == 3)
                return 1;
            else
                return 0;
        }
        if (world[i][j] == 0 && counter == 3)
            return 1;
        return world[i][j];
    }
}
