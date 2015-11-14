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

    public int[][] parseJson(String gen) throws IOException {
        return new ObjectMapper().reader(int[][].class).readValue(gen);
    }
}