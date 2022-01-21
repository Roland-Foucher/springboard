package co.simplon.p16.springboard.repository;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.sql.SQLException;

import javax.sql.DataSource;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceUtils;

import co.simplon.p16.springboard.entity.Artist;
import co.simplon.p16.springboard.entity.Track;

public class TrackRepositoryTest {

    
    TrackRepository trackRepository;


    @BeforeEach
    public void init(){
        trackRepository = new TrackRepository();
    }
    

    @Test
    public void testAddAndDeleteTrackInDatabase(){
       
        Track track = new Track("name", "url");
        track.setArtistId(1);
        assertTrue(trackRepository.save(track)); 
        assertTrue(trackRepository.deleteById(track.getId()));
    }
    @Test
    public void testFindByIdandUpdateTrackInDatabase(){
        Track track = trackRepository.findById(1);
        assertTrue(trackRepository.update(track)); 
        
    }
    
}
