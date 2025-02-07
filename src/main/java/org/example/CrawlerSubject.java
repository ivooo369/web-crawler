package org.example;

import java.util.ArrayList;
import java.util.List;

/**
 * Клас, който се използва за уведомяване при настъпване на промяна или събитие.
 */
public class CrawlerSubject {
    private final List<CrawlerObserver> observers = new ArrayList<>();

    /**
     * Добавя нов наблюдател към списъка с наблюдатели.
     *
     * @param observer Наблюдателят, който ще бъде добавен.
     */
    public void addObserver(CrawlerObserver observer) {
        observers.add(observer);
    }

    /**
     * Известява всички наблюдатели с предоставеното съобщение.
     *
     * @param message Съобщението, което ще бъде изпратено на всички наблюдатели.
     */
    public void notifyObservers(String message) {
        for (CrawlerObserver observer : observers) {
            observer.update(message);
        }
    }
}
