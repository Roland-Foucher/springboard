package co.simplon.p16.springboard.repositorySpringtest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import javax.sql.DataSource;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;

import co.simplon.p16.springboard.entity.SocialNetwork;
import co.simplon.p16.springboard.repository.SocialNetworkRepository;

@SpringBootTest
@ActiveProfiles("test")
@Sql(scripts={"/schema.sql", "/data.sql"}, executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
class SocialNetworkRepositoryTest {

    @Autowired
    SocialNetworkRepository socialNetworkRepository;


    @Test
    void testupdateTrack() {

        SocialNetwork socialNetwork = new SocialNetwork(1, "url", "name", 1);
        assertTrue(socialNetworkRepository.update(socialNetwork));
    }

    @Test
    void testDeleteTrack() {
        assertTrue(socialNetworkRepository.deleteById(1));
    }

    @Test
    void testFindById() {
        assertNotNull(socialNetworkRepository.findById(1));
    }

    @Test
    void testFindAll() {

        assertNotEquals(0,socialNetworkRepository.findAll());
    }

    @Test
    void testDeleteByArtistId() {
        assertNotNull(socialNetworkRepository.deleteByArtistId(1));
    }

    @Test
    void testDeleteMultipleSocialNetworkByArtistId() {

        assertNotNull(socialNetworkRepository.deleteByArtistId(1));
        assertEquals(0, socialNetworkRepository.findByArtistId(1).size());
    }

    @Test
    void testFindByArtistId() {
        assertNotNull(socialNetworkRepository.findByArtistId(1));

    }

    @Test
    void testFindMultipleSocialNetworkByArtiste() {
        assertEquals(2, socialNetworkRepository.findByArtistId(1).size());
    }
}
