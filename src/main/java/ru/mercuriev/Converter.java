package ru.mercuriev;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;
import com.fasterxml.jackson.databind.ObjectWriter;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.IOException;

@Component
public class Converter {

    private ObjectWriter writer;
    private ObjectReader reader;

    @PostConstruct
    public void init(){
        writer = new ObjectMapper().writerWithType(int[][].class);
        reader = new ObjectMapper().reader(int[][].class);
    }

    public String toJson(int[][] world) throws JsonProcessingException {
        return writer.writeValueAsString(world);
    }

    public int[][] fromJson(String worldAsString) throws IOException {
        return reader.readValue(worldAsString);
    }
}