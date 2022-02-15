package co.simplon.p16.springboard.repositorySpringtest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;

import co.simplon.p16.springboard.entity.User;
import co.simplon.p16.springboard.repository.UserRepository;

@SpringBootTest
@ActiveProfiles("test")
@Sql(scripts={"/schema.sql", "/data.sql"}, executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
public class UserRepositoryTest {

    @Autowired
    UserRepository userRepository;
  

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
