import org.example.CrawlerObserver;
import org.example.CrawlerSubject;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.*;

/**
 * Тестов клас за проверка на функционалността на {@link CrawlerSubject}.
 */
public class CrawlerSubjectTest {

    /**
     * Тества добавянето на наблюдател към {@link CrawlerSubject} и
     * проверява дали уведомяването работи коректно.
     */
    @Test
    public void testAddObserver() {
        CrawlerObserver observer = mock(CrawlerObserver.class);
        CrawlerSubject subject = new CrawlerSubject();
        subject.addObserver(observer);
        subject.notifyObservers("Тестово съобщение");
        verify(observer).update("Тестово съобщение");
    }

    /**
     * Тества дали всички добавени наблюдатели получават уведомления.
     */
    @Test
    public void testNotifyObservers() {
        CrawlerObserver observer1 = mock(CrawlerObserver.class);
        CrawlerObserver observer2 = mock(CrawlerObserver.class);
        CrawlerSubject subject = new CrawlerSubject();
        subject.addObserver(observer1);
        subject.addObserver(observer2);
        subject.notifyObservers("Тестово съобщение");
        verify(observer1).update("Тестово съобщение");
        verify(observer2).update("Тестово съобщение");
    }

    /**
     * Тества поведението на {@link CrawlerSubject} при извикване на
     * {@code notifyObservers} без налични наблюдатели.
     * Очаква се методът да не хвърля изключения.
     */
    @Test
    public void testNotifyObserversWithoutObservers() {
        CrawlerSubject subject = new CrawlerSubject();
        subject.notifyObservers("Тестово съобщение");
    }
}
