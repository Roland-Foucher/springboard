package co.simplon.p16.springboard.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;

import javax.sql.DataSource;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

import co.simplon.p16.springboard.entity.Show;

public class ShowRepositoryTest {

    ShowRepository showRepository;

    @BeforeEach
    void init() {
        showRepository = new ShowRepository();
        DataSource dataSource = new EmbeddedDatabaseBuilder()
                .setType(EmbeddedDatabaseType.H2)
                .addScript("springboard-test.sql")
                .build();
        showRepository.setDataSource(dataSource);

    }

    @Nested
    class testBasicCRUD {

        @Test
        public void testSaveShow() {
            Show show = new Show(LocalDate.of(2020, 07, 11), "venue", "adress");
            assertTrue(showRepository.save(show));
            assertNotNull(show.getId());
        }

        @Test
        public void testFindShow() {
            assertNotNull(showRepository.findById(1));
        }

        @Test
        public void testFindAllShow() {
            assertNotNull(showRepository.findAll());
        }

        @Test
        public void testUpdateShow() {
            Show show = new Show(LocalDate.of(2020, 07, 11), "venue", "adress");
            show.setId(1);
            assertTrue(showRepository.update(show));
        }

        @Test
        public void deleteShow() {
            assertTrue(showRepository.deleteById(1));
        }
    }

    @Test
    void testFindByArtist() {
        assertEquals(2, showRepository.findByArtist(1).size());
    }

    @Test
    void testFindByAdress() {
        assertEquals(2, showRepository.findByAdress("test").size());
    }

    @Test
    void testFindByVenue() {
        assertEquals(2, showRepository.findByVenue("venue").size());
    }

    @Test
    void testFindByDate() {
        assertEquals(1, showRepository.findByDate(LocalDate.of(2021, 01, 01)).size());
    }

    @Test
    void deleteShowInArtistsShowTable() {
        assertEquals(2, showRepository.deleteShowInArtistsShowTable(1));
    }
}
