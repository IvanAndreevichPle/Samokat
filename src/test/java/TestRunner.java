import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import ru.praktikum_services.qa_scooter.parametrized.OrderTest;
import ru.praktikum_services.qa_scooter.parametrized.QuestionTest;

@RunWith(Suite.class)
@Suite.SuiteClasses({QuestionTest.class, OrderTest.class})
public class TestRunner {
}