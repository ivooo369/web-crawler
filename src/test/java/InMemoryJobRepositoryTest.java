import org.example.InMemoryJobRepository;
import org.example.JobListing;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Тестов клас за проверка на функционалността на {@link InMemoryJobRepository}.
 */
public class InMemoryJobRepositoryTest {

    /**
     * Тества методите {@code save} и {@code findAll}, като проверява
     * дали записаната обява за работа се съхранява и извлича коректно.
     */
    @Test
    public void testSaveAndFindAll() {
        InMemoryJobRepository repository = new InMemoryJobRepository();

        JobListing job = new JobListing("Software Engineer", "https://test.com/job");

        repository.save(job);

        assertEquals(1, repository.findAll().size(), "Трябва да има един запис в хранилището.");
        assertEquals("Software Engineer", repository.findAll().get(0).title(),
                "Заглавието на работата трябва да е 'Software Engineer'.");
    }
}
