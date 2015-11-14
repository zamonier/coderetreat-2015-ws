package ru.mercuriev.suites;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import ru.mercuriev.ConverterTest;
import ru.mercuriev.EngineTest;

/**
 * Created by Eugene on 15.11.15.
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({ConverterTest.class, EngineTest.class})
public class UnitTestsSuite {
}
