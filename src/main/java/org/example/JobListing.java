package org.example;

/**
 * Рекорд, който представлява обява за работа с нейното заглавие и URL адрес.
 * Използва се за съхранение на информация за конкретна обява за работа.
 */
public record JobListing(String title, String url) {
    /**
     * Конструктор, който инициализира обявата с предоставеното заглавие и URL адрес.
     *
     * @param title Заглавие на обявата.
     * @param url   URL адрес на обявата.
     */
    public JobListing {
    }

    /**
     * Връща заглавието на обявата.
     *
     * @return Заглавие на обявата.
     */
    @Override
    public String title() {
        return title;
    }

    /**
     * Връща URL адреса на обявата.
     *
     * @return URL адрес на обявата.
     */
    @Override
    public String url() {
        return url;
    }
}
