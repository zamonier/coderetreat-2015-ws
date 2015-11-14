package ru.mercuriev;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.assertEquals;

/**
 * Created by Eugene on 15.11.15.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
public class ControllerTest implements Fixture{

    @Autowired
    Controller gameOfLive = new Controller();

    @Test
    public void nextGeneration() throws Exception {
        String nextGen = gameOfLive.nextGeneration(GENERATION_AS_JSON);
        assertEquals(NEXT_GENERATION_AS_JSON, nextGen);
    }

}