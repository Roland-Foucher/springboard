package co.simplon.p16.springboard.repositoryIntegrationTest;

import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import co.simplon.p16.springboard.entity.Artist;
import co.simplon.p16.springboard.entity.User;
import co.simplon.p16.springboard.repository.ArtistRepository;
import co.simplon.p16.springboard.repository.UserRepository;

@SpringBootTest
public class RepositoryIntegrationTest {
    
    @Autowired
    ArtistRepository artistRepository;
    @Autowired
    UserRepository userRepository;

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
        boolean isok = artistRepository.update(artist);
        assertTrue(isok);
        assertTrue(artistRepository.deleteById(artist.getId()));
        assertTrue(userRepository.deleteById(user.getId()));
    }
    
}
