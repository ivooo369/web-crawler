import org.example.CrawlerConfig;
import org.example.ConfigSingleton;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Тестов клас за проверка на Singleton имплементацията на {@link ConfigSingleton}.
 */
public class ConfigSingletonTest {

    /**
     * Тества, че {@link ConfigSingleton#getInstance(int, Set)} винаги връща една и съща инстанция,
     * независимо от параметрите, които се подават.
     */
    @Test
    public void testSingletonInstance() {
        Set<String> websites = new HashSet<>();
        websites.add("https://test.com");

        CrawlerConfig config1 = ConfigSingleton.getInstance(5, websites);
        CrawlerConfig config2 = ConfigSingleton.getInstance(10, websites);

        assertSame(config1, config2, "Трябва да се върне една и съща инстанция.");
    }
}
