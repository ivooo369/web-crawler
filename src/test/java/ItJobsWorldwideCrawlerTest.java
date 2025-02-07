import org.junit.jupiter.api.Test;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.example.ItJobsWorldwideCrawler;
import org.example.JobListing;
import org.example.JobRepository;

import java.util.List;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Тестов клас за {@link ItJobsWorldwideCrawler}.
 * Проверява правилното извличане и съхраняване на обяви за работа от уебсайта itjobsworldwide.com.
 */
public class ItJobsWorldwideCrawlerTest {

    /**
     * Тества метода extractJobListings, като проверява дали правилно извлича
     * заглавията и URL адресите на обявите за работа от HTML документа.
     */
    @Test
    public void testExtractJobListings() {
        String html = "<html><body><div class='results-list'>" +
                "<div class='job-title'><a href='/job1'>Job Title 1</a></div>" +
                "<div class='job-title'><a href='/job2'>Job Title 2</a></div>" +
                "</div></body></html>";

        Document doc = Jsoup.parse(html);
        ItJobsWorldwideCrawler crawler = new ItJobsWorldwideCrawler();

        List<JobListing> jobListings = crawler.extractJobListings(doc, "https://www.itjobsworldwide.com/jobs/backend-development");

        assertEquals(2, jobListings.size(), "Трябва да бъдат извлечени 2 обяви за работа.");
        assertEquals("https://www.itjobsworldwide.com/job1", jobListings.get(0).url(), "URL адресът на първата обява е неправилен.");
        assertEquals("Job Title 1", jobListings.get(0).title(), "Заглавието на първата обява е неправилно.");
        assertEquals("https://www.itjobsworldwide.com/job2", jobListings.get(1).url(), "URL адресът на втората обява е неправилен.");
        assertEquals("Job Title 2", jobListings.get(1).title(), "Заглавието на втората обява е неправилно.");
    }

    /**
     * Тества метода saveJobListings, като проверява дали обявите се запазват
     * правилно в хранилището.
     */
    @Test
    public void testSaveJobListings() {
        JobRepository repository = mock(JobRepository.class);
        JobListing jobListing = new JobListing("Job Title 1", "https://www.itjobsworldwide.com/job1");
        ItJobsWorldwideCrawler crawler = new ItJobsWorldwideCrawler();

        crawler.saveJobListings(List.of(jobListing), repository);

        verify(repository, times(1)).save(jobListing);
    }

    /**
     * Тества метода craw, като проверява дали обявите се извличат и запазват коректно.
     */
    @Test
    public void testCraw() {
        String html = "<html><body><div class='results-list'>" +
                "<div class='job-title'><a href='/job1'>Job Title 1</a></div>" +
                "<div class='job-title'><a href='/job2'>Job Title 2</a></div>" +
                "</div></body></html>";

        Document doc = Jsoup.parse(html);
        JobRepository repository = mock(JobRepository.class);
        ItJobsWorldwideCrawler crawler = new ItJobsWorldwideCrawler();

        crawler.craw(repository, "https://www.itjobsworldwide.com/jobs/backend-development", doc);

        verify(repository, times(2)).save(any(JobListing.class));
    }
}

