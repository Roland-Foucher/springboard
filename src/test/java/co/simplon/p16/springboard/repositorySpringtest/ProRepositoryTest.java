package co.simplon.p16.springboard.repositorySpringtest;

import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;

import co.simplon.p16.springboard.entity.Pro;
import co.simplon.p16.springboard.repository.ProRepository;

@SpringBootTest
@ActiveProfiles("test")
@Sql(scripts={"/schema.sql", "/data.sql"}, executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
public class ProRepositoryTest {

    @Autowired
    ProRepository proRepository;

    

    @Nested
    class testBasicCRUD {

        @Test
        void testSaveArtist() {
            Pro artist = new Pro("companyName", "activity", "contact", "city", "siret");
            artist.setUserId(3);

            assertTrue(proRepository.save(artist));
            assertNotNull(artist.getId());
        }

        @Test
        void testFindArtist() {
    
            assertNotNull(proRepository.findById(1));
        }

        @Test
        void testFindAllArtist() {
            assertNotEquals(0,proRepository.findAll());
        }

        @Test
        void testUpdateArtist() {
            Pro artist = new Pro(1, "companyName", "activity", "contact", "city", "siret", 1);
            assertTrue(proRepository.update(artist));
        }

        @Test
        void deleteArtistById() {
            assertTrue(proRepository.deleteById(1));
        }
    }

    @Test
    void testFindByUser() {
        assertNotNull(proRepository.findByUser(1));
    }

}
