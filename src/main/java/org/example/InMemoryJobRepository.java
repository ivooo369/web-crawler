package org.example;

import java.util.ArrayList;
import java.util.List;

/**
 * Имплементация на {@link JobRepository}, която съхранява обяви за работа в паметта.
 * Тази имплементация не използва външна база данни, а добавя обявите в локален списък.
 * Използва се за временни операции.
 */
public class InMemoryJobRepository implements JobRepository {
    private final List<JobListing> jobListings = new ArrayList<>();

    /**
     * Записва нова обява за работа в паметта.
     *
     * @param job Обявата за работа, която ще бъде записана.
     */
    @Override
    public void save(JobListing job) {
        jobListings.add(job);
    }

    /**
     * Връща всички обяви за работа, съхранени в паметта.
     *
     * @return Списък с всички обяви за работа.
     */
    @Override
    public List<JobListing> findAll() {
        return new ArrayList<>(jobListings);
    }
}
