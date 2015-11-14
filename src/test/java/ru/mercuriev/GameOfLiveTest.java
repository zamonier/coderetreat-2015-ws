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
@SpringApplicationConfiguration(classes = DemoApplication.class)
public class GameOfLiveTest implements Fixture{

    @Autowired
    GameOfLive gameOfLive = new GameOfLive();

    @Test
    public void testNextGen() throws Exception {
        String nextGen = gameOfLive.nextGen(GENERATION);
        assertEquals(NEXT_GENERATION_AS_JSON, nextGen);
    }

}