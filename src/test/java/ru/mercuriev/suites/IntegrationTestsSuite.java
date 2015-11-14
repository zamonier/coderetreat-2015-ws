package ru.mercuriev.suites;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import ru.mercuriev.ApplicationTest;
import ru.mercuriev.ControllerTest;

/**
 * Created by Eugene on 15.11.15.
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({ApplicationTest.class, ControllerTest.class})
public class IntegrationTestsSuite {
}
