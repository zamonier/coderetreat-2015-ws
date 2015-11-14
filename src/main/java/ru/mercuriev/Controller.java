package ru.mercuriev;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;

@org.springframework.stereotype.Controller
public class Controller {

    @Autowired
    private Converter converter;

    @Autowired
    private Engine engine;

    @RequestMapping("/next-gen")
    @ResponseBody
    public String nextGeneration(@RequestParam String gen) throws IOException {
        return converter.toJson(engine.nextGeneration(converter.fromJson(gen)));
    }

}
