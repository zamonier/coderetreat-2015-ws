package ru.mercuriev;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.mercuriev.game.of.life.array.ArrayEngine;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
public class Controller {

    @Autowired
    private ArrayEngine engine;

    @CrossOrigin
    @RequestMapping(value = "/next", method = RequestMethod.POST)
    public int[][] next(@RequestBody int[][] generation, HttpServletResponse response) throws IOException {
        return engine.next(generation);
    }
}
