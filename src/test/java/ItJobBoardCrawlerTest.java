import org.junit.jupiter.api.Test;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.example.ItJobBoardCrawler;
import org.example.JobListing;
import org.example.JobRepository;

import java.util.List;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Тестов клас за {@link ItJobBoardCrawler}.
 * Проверява правилното извличане и съхраняване на обяви за работа.
 */
public class ItJobBoardCrawlerTest {

    /**
     * Тества метода extractJobListings, като проверява дали правилно извлича
     * заглавията и URL адресите на обявите от HTML документа.
     */
    @Test
    public void testExtractJobListings() {
        String html = "<html><body><div class='search-results'>" +
                "<a class='link' href='/job1'><h3 class='media-heading'>Job Title 1</h3></a>" +
                "<a class='link' href='/job2'><h3 class='media-heading'>Job Title 2</h3></a>" +
                "</div></body></html>";

        Document doc = Jsoup.parse(html);
        ItJobBoardCrawler crawler = new ItJobBoardCrawler();

        List<JobListing> jobListings = crawler.extractJobListings(doc, "https://www.itjobboard.co.uk");

        assertEquals(2, jobListings.size(), "Трябва да бъдат извлечени 2 обяви за работа.");
        assertEquals("https://www.itjobboard.co.uk/job1", jobListings.get(0).url(), "URL адресът на първата обява е неправилен.");
        assertEquals("Job Title 1", jobListings.get(0).title(), "Заглавието на първата обява е неправилно.");
    }

    /**
     * Тества метода saveJobListings, като проверява дали обявите се запазват
     * правилно в хранилището.
     */
    @Test
    public void testSaveJobListings() {
        JobRepository repository = mock(JobRepository.class);
        JobListing jobListing = new JobListing("Job Title 1", "https://www.itjobboard.co.uk/job1");
        ItJobBoardCrawler crawler = new ItJobBoardCrawler();

        crawler.saveJobListings(List.of(jobListing), repository);

        verify(repository, times(1)).save(jobListing);
    }

    /**
     * Тества метода craw, като проверява дали обявите се извличат и запазват коректно.
     */
    @Test
    public void testCraw() {
        String html = "<html><body><div class='search-results'>" +
                "<a class='link' href='/job1'><h3 class='media-heading'>Job Title 1</h3></a>" +
                "<a class='link' href='/job2'><h3 class='media-heading'>Job Title 2</h3></a>" +
                "</div></body></html>";

        Document doc = Jsoup.parse(html);
        JobRepository repository = mock(JobRepository.class);
        ItJobBoardCrawler crawler = new ItJobBoardCrawler();

        crawler.craw(repository, "https://www.itjobboard.co.uk", doc);

        verify(repository, times(2)).save(any(JobListing.class));
    }
}
