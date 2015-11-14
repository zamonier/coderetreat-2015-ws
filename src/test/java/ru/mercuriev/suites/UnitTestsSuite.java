package ru.mercuriev.suites;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import ru.mercuriev.ConverterTest;
import ru.mercuriev.CounterTest;
import ru.mercuriev.EngineTest;
import ru.mercuriev.NextStateTest;

/**
 * Created by Eugene on 15.11.15.
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({ConverterTest.class, NextStateTest.class, CounterTest.class, EngineTest.class})
public class UnitTestsSuite {
}
