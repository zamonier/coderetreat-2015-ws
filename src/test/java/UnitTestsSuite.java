import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import ru.mercuriev.game.of.life.array.NeighboursCounterTest;
import ru.mercuriev.game.of.life.array.EngineTest;
import ru.mercuriev.game.of.life.array.NextStateTest;

@RunWith(Suite.class)
@Suite.SuiteClasses({NextStateTest.class, NeighboursCounterTest.class, EngineTest.class})
public class UnitTestsSuite {
}
