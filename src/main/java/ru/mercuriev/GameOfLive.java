package ru.mercuriev;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;

/**
 * Created by Eugene on 14.11.15.
 */
@Controller
public class GameOfLive {

    @Autowired
    private Converter converter;

    @Autowired
    private GameOfLiveEngine gameOfLiveEngine;

    @RequestMapping("/next-gen")
    @ResponseBody
    public String nextGen(@RequestParam String gen) throws IOException {
        return converter.print(gameOfLiveEngine.calculate(converter.parseJson(gen)));
    }

}
