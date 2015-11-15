package ru.mercuriev;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;

@org.springframework.stereotype.Controller
public class Controller {

    @Autowired
    private Engine engine;

    @RequestMapping(value = "/next", method = RequestMethod.POST)
    @ResponseBody
    public int[][] next(@RequestBody int[][] gen) throws IOException {
        return engine.nextGeneration(gen);
    }

    @RequestMapping("/")
    public String index() {
        return "redirect:index.html";
    }

}
