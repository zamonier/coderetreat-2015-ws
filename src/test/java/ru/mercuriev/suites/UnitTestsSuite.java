package ru.mercuriev.suites;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import ru.mercuriev.CounterTest;
import ru.mercuriev.EngineTest;
import ru.mercuriev.NextStateTest;

@RunWith(Suite.class)
@Suite.SuiteClasses({NextStateTest.class, CounterTest.class, EngineTest.class})
public class UnitTestsSuite {
}
