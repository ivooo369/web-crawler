package org.example;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

/**
 * Клас, който представлява кролер, отговорен за извличането на обяви за работа от уебсайтове.
 * Класът използва стратегия за обхождане, конфигурация за кролера и хранилище за обяви.
 * Този клас извършва паралелно извличане на данни от предоставените уебсайтове, като използва CompletableFuture
 * за асинхронни операции.
 */
public class WebCrawler {

    private final JobRepository repository;
    private final CrawlerConfig config;
    private final CrawlerStrategy crawlerStrategy;

    /**
     * Конструктор за създаване на инстанция на кролера с конфигурация, хранилище и стратегия.
     *
     * @param repository   Хранилище за запазване на извлечените обяви.
     * @param config       Конфигурация за кролера, включително целевите уебсайтове и максималния брой паралелни заявки.
     * @param crawlerStrategy Стратегия за извличане на обяви от даден уебсайт.
     */
    public WebCrawler(JobRepository repository, CrawlerConfig config, CrawlerStrategy crawlerStrategy) {
        this.repository = repository;
        this.config = config;
        this.crawlerStrategy = crawlerStrategy;
    }

    /**
     * Започва процеса на обхождане на уебсайтовете за обяви за работа.
     * Извършва асинхронни заявки към уебсайтовете и при получаване на отговори извиква стратегията за извличане на обяви.
     * Използва се за обработка на множество уебсайтове едновременно с ограничен брой паралелни заявки.
     */
    public void crawl() {
        List<CompletableFuture<Void>> futures = new ArrayList<>();

        for (String website : config.targetWebsites()) {
            CompletableFuture<Void> future = CompletableFuture.runAsync(() -> {
                try {
                    // Извършва HTTP заявка към уебсайта и получава HTML документа.
                    Document doc = Jsoup.connect(website).timeout(10000).get();

                    // Извиква стратегията за извличане на обяви.
                    crawlerStrategy.craw(repository, website, doc);

                } catch (IOException e) {
                    e.printStackTrace();
                }
            });

            futures.add(future);

            // Ако броят на текущите паралелни заявки е достигнал максимума, чакаме за завършване на всички.
            if (futures.size() >= config.maxParallelRequests()) {
                CompletableFuture.allOf(futures.toArray(new CompletableFuture[0])).join();
                futures.clear();
            }
        }

        // Чакаме всички асинхронни задачи да завършат.
        CompletableFuture.allOf(futures.toArray(new CompletableFuture[0])).join();
    }
}

