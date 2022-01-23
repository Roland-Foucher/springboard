package co.simplon.p16.springboard.repository;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import javax.sql.DataSource;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

import co.simplon.p16.springboard.entity.User;

public class UserRepositoryTest {

    UserRepository userRepository;
    ArtistRepository artistRepository;
    ProRepository proRepository;
    SocialNetworkRepository socialNetworkRepository;
    TrackRepository trackRepository;

    @BeforeEach
    public void init() {
            userRepository = new UserRepository();
            artistRepository = new ArtistRepository();
            proRepository = new ProRepository();
            socialNetworkRepository = new SocialNetworkRepository();
            trackRepository = new TrackRepository();

            artistRepository.setSocialNetworkRepository(socialNetworkRepository);
            artistRepository.setTrackRepository(trackRepository);
            userRepository.setArtistRepository(artistRepository);
            userRepository.setProRepository(proRepository);


            DataSource dataSource = new EmbeddedDatabaseBuilder()
                    .setType(EmbeddedDatabaseType.H2)
                    .addScript("springboard-test.sql")
                    .build();
            userRepository.setDataSource(dataSource);
            artistRepository.setDataSource(dataSource);
            proRepository.setDataSource(dataSource);
            trackRepository.setDataSource(dataSource);
            socialNetworkRepository.setDataSource(dataSource);
    }

    @Test
    void testFindByEmail() {
        assertNotNull(userRepository.findByEmail("test"));
    }

    @Nested
    class testBasicCRUD {

        @Test
        public void testSaveUser() {
            User user = new User("firstName", "lastName", "email", "password", "role");
            assertTrue(userRepository.save(user));
            assertNotNull(user.getId());
        }


        @Test
        public void testFindUser() {
            assertNotNull(userRepository.findById(1));
        }

        @Test
        public void testFindAllUser() {
            assertNotNull(userRepository.findAll());
        }

        @Test
        public void testUpdateUser() {
            User user = new User("firstName", "lastName", "email", "password", "role");
            user.setId(1);
            assertTrue(userRepository.update(user));
        }
        @Test
        public void deleteUser(){
            assertTrue(userRepository.deleteById(1));
        }
    }
}
