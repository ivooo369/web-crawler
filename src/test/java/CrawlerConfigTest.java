import org.example.CrawlerConfig;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Тестов клас за проверка на функционалността на {@link CrawlerConfig}.
 */
public class CrawlerConfigTest {

    /**
     * Тества конструктора на {@link CrawlerConfig} и проверява дали
     * getter методите връщат коректни стойности.
     */
    @Test
    public void testConfigConstructorAndGetters() {
        Set<String> websites = new HashSet<>();
        websites.add("https://test.com");

        CrawlerConfig config = new CrawlerConfig(5, websites);

        assertEquals(5, config.maxParallelRequests(), "Максималният брой заявки трябва да е 5.");
        assertEquals(websites, config.targetWebsites(), "Целевите уебсайтове трябва да съвпадат.");
    }
}
