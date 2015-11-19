import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import ru.mercuriev.game.of.life.NeighboursCounterTest;
import ru.mercuriev.game.of.life.EngineTest;
import ru.mercuriev.game.of.life.NextStateTest;

@RunWith(Suite.class)
@Suite.SuiteClasses({NextStateTest.class, NeighboursCounterTest.class, EngineTest.class})
public class UnitTestsSuite {
}
