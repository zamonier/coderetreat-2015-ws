package ru.mercuriev;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
public class ControllerTest implements Fixture {

    @Autowired
    Controller controller = new Controller();

    @Test
    public void testNextGeneration() throws Exception {
        assertEquals(NEXT_GENERATION_AS_JSON, controller.nextGeneration(GENERATION_AS_JSON));
    }

}