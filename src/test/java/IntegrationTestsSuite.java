import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import ru.mercuriev.ApplicationTest;
import ru.mercuriev.ControllerTest;

@RunWith(Suite.class)
@Suite.SuiteClasses({ApplicationTest.class, ControllerTest.class})
public class IntegrationTestsSuite {
}
