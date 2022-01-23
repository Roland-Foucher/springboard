package co.simplon.p16.springboard.repository;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import javax.sql.DataSource;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.test.context.ActiveProfiles;

import co.simplon.p16.springboard.entity.Track;


public class TrackRepositoryTest {
    
   
    TrackRepository trackRepository;

    @BeforeEach
     void init() {
            trackRepository = new TrackRepository();
            DataSource dataSource = new EmbeddedDatabaseBuilder()
                    .setType(EmbeddedDatabaseType.H2)
                    .addScript("springboard-test.sql")
                    .build();
            trackRepository.setDataSource(dataSource);
    }

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
    void testDeleteTrack(){
        assertTrue(trackRepository.deleteById(1));
    }

    @Test 
    void testFindById(){
        assertNotNull(trackRepository.findById(1));
    }

    @Test
     void testFindAll(){

        assertNotNull(trackRepository.findAll());
    }

    @Test
    void testDeleteByArtist(){
        assertTrue(trackRepository.deleteByArtistId(1));
    }
    @Test
    void testDeleteMultipleTracksByArtist(){
        Track track = new Track(2,"name", "url",1);
        trackRepository.save(track);
        trackRepository.deleteByArtistId(1);
        assertNull(trackRepository.findByArtistId(1));
    }




}
