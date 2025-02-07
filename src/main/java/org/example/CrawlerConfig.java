package org.example;

import java.util.Set;

/**
 * Рекорд, който съхранява конфигурационни настройки за кролера.
 * Тези настройки включват максималния брой паралелни заявки и
 * целевите уебсайтове, които ще бъдат обходени.
 */
public record CrawlerConfig(int maxParallelRequests, Set<String> targetWebsites) {

    /**
     * Създава нова инстанция на {@link CrawlerConfig} със зададени конфигурационни параметри.
     *
     * @param maxParallelRequests Максимален брой паралелни заявки.
     * @param targetWebsites      Сет от уебсайтове, които трябва да бъдат обходени.
     */
    public CrawlerConfig {
    }

    /**
     * Връща максималния брой паралелни заявки.
     *
     * @return Максимален брой паралелни заявки.
     */
    @Override
    public int maxParallelRequests() {
        return maxParallelRequests;
    }

    /**
     * Връща сет с уебсайтове, които трябва да бъдат обходени.
     *
     * @return Сет с уебсайтове.
     */
    @Override
    public Set<String> targetWebsites() {
        return targetWebsites;
    }
}
