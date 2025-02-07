package org.example;

/**
 * Наблюдател, който записва съобщения в лог.
 * Класът имплементира интерфейса {@link CrawlerObserver} и предоставя метод за обработка на съобщения,
 * които се извеждат на конзолата като логови съобщения.
 */
public class LoggerObserver implements CrawlerObserver {

    /**
     * Обработва полученото съобщение и го извежда на конзолата.
     *
     * @param message Съобщението, което трябва да бъде записано в лог.
     */
    @Override
    public void update(String message) {
        System.out.println("\nLOG: " + message);
    }
}
