package co.simplon.p16.springboard.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import javax.sql.DataSource;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

import co.simplon.p16.springboard.entity.Track;

public class TrackRepositoryTest {

    private TrackRepository trackRepository;

    @BeforeEach
    void init() {
        trackRepository = new TrackRepository();
        DataSource dataSource = new EmbeddedDatabaseBuilder()
                .setType(EmbeddedDatabaseType.H2)
                .addScript("springboard-test.sql")
                .build();
        trackRepository.setDataSource(dataSource);
    }

    @Nested
    class testBasicCrud {

        @Test
        void testSaveTrack() {

            Track track = new Track("name", "url");
            track.setArtistId(1);
            assertTrue(trackRepository.save(track));
            assertNotNull(track.getId());
        }

        @Test
        void testupdateTrack() {

            Track track = new Track("name", "url");
            track.setArtistId(1);
            track.setId(1);
            assertTrue(trackRepository.update(track));
        }

        @Test
        void testDeleteTrack() {
            assertTrue(trackRepository.deleteById(1));
        }

        @Test
        void testFindById() {
            assertNotNull(trackRepository.findById(1));
        }

        @Test
        void testFindAll() {

            assertNotEquals(0, trackRepository.findAll().size());
        }
    }

    @Test
    void testDeleteByArtist() {
        assertNotNull(trackRepository.deleteByArtistId(1));
    }

    @Test
    void testDeleteMultipleTracksByArtist() {
        assertNotNull(trackRepository.deleteByArtistId(1));
        assertEquals(0, trackRepository.findByArtistId(1).size());
    }

    @Test
    void testFindByArtistId() {
        assertNotEquals(0, trackRepository.findByArtistId(1).size());
    }

    @Test
    void testFindMultiplesTracksByArtistId() {

        assertEquals(2, trackRepository.findByArtistId(1).size());
    }

}
