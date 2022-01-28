package co.simplon.p16.springboard.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import javax.sql.DataSource;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

import co.simplon.p16.springboard.entity.SocialNetwork;

class SocialNetworkRepositoryTest {

    SocialNetworkRepository socialNetworkRepository;

    @BeforeEach
    void init() {
        socialNetworkRepository = new SocialNetworkRepository();
        DataSource dataSource = new EmbeddedDatabaseBuilder()
                .setType(EmbeddedDatabaseType.H2)
                .addScript("springboard-test.sql")
                .build();
        socialNetworkRepository.setDataSource(dataSource);
    }

    @Test
    void testSaveTrack() {

        SocialNetwork socialNetwork = new SocialNetwork("url", "name");
        socialNetwork.setArtistId(1);
        assertTrue(socialNetworkRepository.save(socialNetwork));
        assertNotNull(socialNetwork.getId());
    }

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

        assertNotNull(socialNetworkRepository.findAll());
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
