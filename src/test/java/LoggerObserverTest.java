import org.example.LoggerObserver;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Тестов клас за {@link LoggerObserver}.
 * Проверява дали LoggerObserver коректно обработва съобщенията при извикване на update().
 */
public class LoggerObserverTest {

    /**
     * Тества метода update(), като проверява дали той се изпълнява
     * без да хвърля изключения при подаване на съобщение.
     */
    @Test
    public void testUpdate() {
        LoggerObserver loggerObserver = new LoggerObserver();

        assertDoesNotThrow(() -> loggerObserver.update("Това е тестово съобщение"),
                "Методът update() не трябва да хвърля изключения.");
    }
}
