import org.example.*;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import java.lang.reflect.Field;
import java.util.*;

/**
 * Тестов клас за {@link WebCrawlerFactory}.
 * Проверява поведението на метода createWebCrawlers в различни сценарии.
 */
class WebCrawlerFactoryTest {

    /**
     * Тества създаването на {@link WebCrawler} обекти, когато целевите уебсайтове съвпадат
     * с правилните стратеги за извличане на данни.
     *
     * @throws NoSuchFieldException ако не се намери полето crawlerStrategy
     * @throws IllegalAccessException ако достъпът до полето е забранен
     */
    @Test
    void testCreateWebCrawlers_withMatchingWebsites() throws NoSuchFieldException, IllegalAccessException {
        JobRepository repository = mock(JobRepository.class);
        CrawlerConfig config = mock(CrawlerConfig.class);

        Set<String> websites = new HashSet<>(List.of(
                "https://www.itjobboard.co.uk/jobs/",
                "https://www.itjobsworldwide.com/jobs/backend-development"
        ));

        when(config.targetWebsites()).thenReturn(websites);

        List<WebCrawler> crawlers = WebCrawlerFactory.createWebCrawlers(repository, config);

        assertEquals(2, crawlers.size(), "Трябва да има 2 създадени WebCrawler обекта.");

        // Проверява дали правилните стратегии са присвоени на всеки crawler
        Field field = crawlers.get(0).getClass().getDeclaredField("crawlerStrategy");
        field.setAccessible(true);

        assertTrue(field.get(crawlers.get(0)) instanceof ItJobBoardCrawler, "Първият crawler трябва да използва ItJobBoardCrawler.");
        assertTrue(field.get(crawlers.get(1)) instanceof ItJobsWorldwideCrawler, "Вторият crawler трябва да използва ItJobsWorldwideCrawler.");
    }

    /**
     * Тества създаването на {@link WebCrawler} обекти, когато целевите уебсайтове не съвпадат
     * с нито една известна стратегия.
     */
    @Test
    void testCreateWebCrawlers_withNoMatchingWebsites() {
        JobRepository repository = mock(JobRepository.class);
        CrawlerConfig config = mock(CrawlerConfig.class);

        Set<String> websites = new HashSet<>(List.of(
                "https://www.someotherwebsite.com"
        ));

        when(config.targetWebsites()).thenReturn(websites);

        List<WebCrawler> crawlers = WebCrawlerFactory.createWebCrawlers(repository, config);

        assertEquals(0, crawlers.size(), "Трябва да се върнат 0 WebCrawler обекта, защото няма съвпадения.");
    }

    /**
     * Тества създаването на {@link WebCrawler} обекти, когато списъкът с целеви уебсайтове е null.
     */
    @Test
    void testCreateWebCrawlers_withNullWebsites() {
        JobRepository repository = mock(JobRepository.class);
        CrawlerConfig config = mock(CrawlerConfig.class);

        when(config.targetWebsites()).thenReturn(null);

        List<WebCrawler> crawlers = WebCrawlerFactory.createWebCrawlers(repository, config);

        assertEquals(0, crawlers.size(), "Трябва да се върнат 0 WebCrawler обекта, ако списъкът с уебсайтове е null.");
    }
}
