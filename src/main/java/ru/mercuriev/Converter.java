package ru.mercuriev;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class Converter {

    public String print(int[][] result) throws JsonProcessingException {
        return new ObjectMapper().writerWithType(int[][].class).writeValueAsString(result);
    }

    public int[][] parse(String gen) {
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

    public int[][] parseJson(String gen) throws IOException {
        return new ObjectMapper().reader(int[][].class).readValue(gen);
    }
}