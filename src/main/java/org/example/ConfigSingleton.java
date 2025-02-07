package org.example;

import java.util.Set;

/**
 * Singleton клас, който осигурява единствена инстанция на {@link CrawlerConfig}.
 * Използва се за съхранение на глобалните конфигурационни настройки за кролера,
 * като максимален брой паралелни заявки и целеви уебсайтове.
 */
public class ConfigSingleton {

    private static CrawlerConfig instance;

    private ConfigSingleton() {}

    /**
     * Връща единствена инстанция на {@link CrawlerConfig}. Ако инстанцията не съществува,
     * тя се създава с предоставените конфигурационни параметри.
     *
     * @param maxParallelRequests Максимален брой паралелни заявки.
     * @param targetWebsites Сет от уебсайтове, които трябва да бъдат обходени.
     * @return Единствената инстанция на {@link CrawlerConfig}.
     */
    public static synchronized CrawlerConfig getInstance(int maxParallelRequests, Set<String> targetWebsites) {
        if (instance == null) {
            instance = new CrawlerConfig(maxParallelRequests, targetWebsites);
        }
        return instance;
    }
}
