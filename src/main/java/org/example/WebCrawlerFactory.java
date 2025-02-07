package org.example;

import java.util.ArrayList;
import java.util.List;

/**
 * Клас за създаване на обекти {@link WebCrawler}.
 * Класът проверява дадените уебсайтове и избира съответната стратегия за обхождане.
 */
public class WebCrawlerFactory {

    /**
     * Създава и връща списък с {@link WebCrawler} за дадените уебсайтове.
     *
     * @param repository Хранилище за съхранение на обявите за работа
     * @param config     Конфигурация, съдържаща целевите уебсайтове
     * @return Списък с инициализирани {@link WebCrawler} обекти
     */
    public static List<WebCrawler> createWebCrawlers(JobRepository repository, CrawlerConfig config) {
        List<WebCrawler> crawlers = new ArrayList<>();

        if (config.targetWebsites() == null || config.targetWebsites().isEmpty()) {
            return crawlers;
        }

        for (String website : config.targetWebsites()) {
            CrawlerStrategy crawlerStrategy = null;

            if (website.contains("itjobboard.co.uk")) {
                crawlerStrategy = new ItJobBoardCrawler();
            } else if (website.contains("itjobsworldwide.com")) {
                crawlerStrategy = new ItJobsWorldwideCrawler();
            }

            if (crawlerStrategy != null) {
                crawlers.add(new WebCrawler(repository, config, crawlerStrategy));
            }
        }

        return crawlers;
    }
}
