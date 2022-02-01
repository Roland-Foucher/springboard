package co.simplon.p16.springboard.repositoryIntegrationTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;

import co.simplon.p16.springboard.entity.Artist;
import co.simplon.p16.springboard.entity.User;
import co.simplon.p16.springboard.repository.ArtistRepository;
import co.simplon.p16.springboard.repository.MusicalStyleRepository;
import co.simplon.p16.springboard.repository.UserRepository;

@SpringBootTest
@ActiveProfiles("test")
@Sql(scripts={"/schema.sql", "/data.sql"}, executionPhase = ExecutionPhase.AFTER_TEST_METHOD)

public class RepositoryIntegrationTest {
    
    @Autowired
    ArtistRepository artistRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    MusicalStyleRepository musicalStyleRepository;

    
    @Test
    void testCRUDWithArtistandUser(){
        User user = new User("firstName", "lastName", "emailTest", "password", "role");
        Artist artist = new Artist("artistName", "coverUrl", "contact", "webSite", "city", "bio", 0, 0, true);
        artist.setMusicalStyleId(1);
        assertTrue(userRepository.save(user));
        artist.setUserId(user.getId());
        assertTrue(artistRepository.save(artist));
        assertNotEquals(0, artistRepository.findById(artist.getId()));
        assertNotEquals(0,artistRepository.findAll());
        assertTrue(artistRepository.update(artist));
        assertTrue(artistRepository.deleteById(artist.getId()));
        assertTrue(userRepository.deleteById(user.getId()));
    }
    @Test
    void testmusicalStyleFindById() {
        assertNotNull(musicalStyleRepository.findById(1));
        assertEquals(3, musicalStyleRepository.findById(3).getId());
    }
}
