package co.simplon.p16.springboard.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import javax.sql.DataSource;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

import co.simplon.p16.springboard.entity.Pro;

public class ProRepositoryTest {

    ProRepository proRepository;

    @BeforeEach
     void init() {
        proRepository = new ProRepository();
        DataSource dataSource = new EmbeddedDatabaseBuilder()
                .setType(EmbeddedDatabaseType.H2)
                .addScript("springboard-test.sql")
                .build();
        proRepository.setDataSource(dataSource);

    }

    @Nested
    class testBasicCRUD {

        @Test
         void testSaveArtist() {
            Pro artist = new Pro("companyName", "activity", "contact", "city", "siret");
            artist.setUserId(1);
            
            assertTrue(proRepository.save(artist));
            assertNotNull(artist.getId());
        }

        @Test
         void testFindArtist() {
            assertNotNull(proRepository.findById(1));
        }

        @Test
         void testFindAllArtist() {
            assertNotNull(proRepository.findAll());
        }

        @Test
         void testUpdateArtist() {
            Pro artist = new Pro(1,"companyName", "activity", "contact", "city", "siret",1);
            assertTrue(proRepository.update(artist));
        }
        @Test
         void deleteArtistById(){
            assertTrue(proRepository.deleteById(1));
        }
    }

    @Test
    void testFindByUser() {
        assertNotNull(proRepository.findByUser(1));
    }

 
}
