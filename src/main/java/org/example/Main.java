package org.example;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Main класът, който стартира процеса на извличане на обяви за работа от уебсайтовете,
 * конфигурира кролера и наблюдателите и извършва обхождане на уебсайтовете.
 * Този клас използва патърни като Singleton, Factory и Observer.
 */
public class Main {

    /**
     * Основен метод, който инициализира конфигурацията на кролера, създава инстанции на кролери
     * и извършва извличането на обяви.
     * Също така уведомява наблюдателите за началото и края на процеса на обхождане.
     *
     * @param args аргументи от командния ред (не се използват в този контекст).
     */
    public static void main(String[] args) {
        Set<String> websites = new HashSet<>(List.of("https://www.itjobboard.co.uk/jobs", "https://www.itjobsworldwide.com/jobs/backend-development"));
        CrawlerConfig config = ConfigSingleton.getInstance(5, websites);

        JobRepository repository = new InMemoryJobRepository();
        List<WebCrawler> webCrawlers = WebCrawlerFactory.createWebCrawlers(repository, config);

        CrawlerSubject subject = new CrawlerSubject();
        subject.addObserver(new LoggerObserver());

        subject.notifyObservers("Starting web crawler...");

        for (WebCrawler webCrawler : webCrawlers) {
            webCrawler.crawl();
        }

        subject.notifyObservers("Web crawler finished.");
    }
}

