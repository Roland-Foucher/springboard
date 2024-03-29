package co.simplon.p16.springboard.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
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
  

    @BeforeEach
    public void init() {
        userRepository = new UserRepository();


        DataSource dataSource = new EmbeddedDatabaseBuilder()
                .setType(EmbeddedDatabaseType.H2)
                .addScript("schema.sql")
                .addScript("data.sql")
                .build();
        userRepository.setDataSource(dataSource);
    }

    @Test
    void testFindByEmail() {
        assertNotNull(userRepository.findByEmail("test"));
    }

    @Nested
    class testBasicCRUD {

        @Test
        void testSaveUser() {
            User user = new User("firstName", "lastName", "email", "password", "role");
            assertTrue(userRepository.save(user));
            assertNotNull(user.getId());
        }

        @Test
        void testFindUser() {
            assertNotNull(userRepository.findById(1));
            assertNull(userRepository.findById(100));
        }

        @Test
        void testFindAllUser() {
            assertNotEquals(0,userRepository.findAll());
        }

        @Test
        void testUpdateUser() {
            User user = new User("firstName", "lastName", "email", "password", "role");
            user.setId(1);
            assertTrue(userRepository.update(user));
        }

        @Test
        void deleteUser() {
            assertTrue(userRepository.deleteById(1));
            
        }
    }

    @Test
    void findByEmail() {
        assertEquals("test",userRepository.findByEmail("test").getEmail());
        assertNull(userRepository.findByEmail(""));
        
    }
    @Test
    void findInvalideUserLoadByUserNameThrowException(){
        assertThrows(Exception.class, () ->{
            userRepository.loadUserByUsername("username");
        });
    }   

    @Nested
    class testFavoritesQueries {
        @Test
        void setFavoriteArtist() {

            assertTrue(userRepository.setFavoriteArtist(2, 2));
        }

        @Test
        void deleteAllUserFavoriteArtist() {
            assertNotNull(userRepository.deleteAllFavoriteArtist(1));
        }

        @Test
        void deleteSingleUserFavoriteArtist() {
            assertTrue(userRepository.deleteSingleFavoriteArtist(1, 1));
        }
    }

    @Nested
    class testUpvotesQueries {
        @Test
        void setUpvoteArtist() {
            assertTrue(userRepository.setUpvote(2, 2));
        }

        @Test
        void deleteAllUserUpvotes() {
            assertNotNull(userRepository.deleteAllUserUpvote(1));
        }
    }

}
