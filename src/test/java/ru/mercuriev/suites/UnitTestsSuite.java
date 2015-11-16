package ru.mercuriev.suites;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import ru.mercuriev.game.of.life.NeighboursCounterTest;
import ru.mercuriev.EngineTest;
import ru.mercuriev.game.of.life.NextStateTest;

@RunWith(Suite.class)
@Suite.SuiteClasses({NextStateTest.class, NeighboursCounterTest.class, EngineTest.class})
public class UnitTestsSuite {
}
