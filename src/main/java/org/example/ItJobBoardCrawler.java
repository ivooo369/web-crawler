package org.example;

import org.jsoup.nodes.Document;

import java.util.ArrayList;
import java.util.List;

/**
 * Имплементация на стратегия за извличане на обяви за работа от "<a href="https://www.itjobboard.co.uk/jobs">...</a>".
 * Класът реализира интерфейса {@link CrawlerStrategy} и предоставя логиката за извличане на обяви от даден HTML документ.
 */
public class ItJobBoardCrawler implements CrawlerStrategy {

    /**
     * Извлича обяви за работа от даден HTML документ и ги записва.
     *
     * @param repository Хранилище за записване на обявите за работа.
     * @param website Уебсайтът, от който са извлечени обявите.
     * @param doc HTML документът, съдържащ резултати за обяви за работа.
     */
    @Override
    public void craw(JobRepository repository, String website, Document doc) {
        List<JobListing> jobListings = extractJobListings(doc, website);
        saveJobListings(jobListings, repository);
    }

    /**
     * Извлича списък с обяви за работа от HTML документа.
     *
     * @param doc HTML документът, съдържащ резултати за обяви за работа.
     * @param website Уебсайтът, от който са извлечени обявите.
     * @return Списък с обяви за работа.
     */
    public List<JobListing> extractJobListings(Document doc, String website) {
        List<JobListing> jobListings = new ArrayList<>();

        doc.select(".search-results").forEach(element -> {
            System.out.println("\n---------- Fetching jobs from: " + website);
            List<String> jobTitles = element.select(".media-heading").eachText();
            List<String> jobUrls = element.select(".link").eachAttr("href");

            for (int i = 0; i < jobTitles.size(); i++) {
                String jobTitle = jobTitles.get(i);
                String jobUrl = jobUrls.get(i);

                if (!jobUrl.startsWith("http")) {
                    jobUrl = website + jobUrl;
                }

                jobListings.add(new JobListing(jobTitle, jobUrl));
            }
        });

        return jobListings;
    }

    /**
     * Записва обявите за работа.
     *
     * @param jobListings Списък с обяви за работа.
     * @param repository Хранилище за записване на обявите за работа.
     */
    public void saveJobListings(List<JobListing> jobListings, JobRepository repository) {
        jobListings.forEach(jobListing -> {
            repository.save(jobListing);
            System.out.println(jobListing.title() + " - " + jobListing.url());
        });
    }
}
