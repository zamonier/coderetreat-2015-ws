import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import ru.mercuriev.game.of.life.array.NeighboursCounterTest;
import ru.mercuriev.game.of.life.array.ArrayEngineTest;
import ru.mercuriev.game.of.life.array.NextStateTest;

@RunWith(Suite.class)
@Suite.SuiteClasses({NextStateTest.class, NeighboursCounterTest.class, ArrayEngineTest.class})
public class UnitTestsSuite {
}
