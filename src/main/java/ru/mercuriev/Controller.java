package ru.mercuriev;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;
import ru.mercuriev.game.of.life.Engine;
import ru.mercuriev.game.of.life.array.ArrayEngine;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
public class Controller {

    @Autowired
    @Qualifier("arrayEngine")
    private Engine engine;

    @CrossOrigin
    @RequestMapping(value = "/next", method = RequestMethod.POST)
    public int[][] next(@RequestBody int[][] generation) throws IOException {
        return engine.next(generation);
    }
}
