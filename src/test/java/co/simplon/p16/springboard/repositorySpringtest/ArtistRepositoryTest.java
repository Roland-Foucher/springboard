package co.simplon.p16.springboard.repositorySpringtest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;

import co.simplon.p16.springboard.entity.Artist;
import co.simplon.p16.springboard.entity.Show;
import co.simplon.p16.springboard.repository.ArtistRepository;
import co.simplon.p16.springboard.repository.ShowRepository;

@SpringBootTest
@ActiveProfiles("test")
@Sql(scripts={"/schema.sql", "/data.sql"}, executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
 class ArtistRepositoryTest {

    @Autowired
    ArtistRepository artistRepository;
    @Autowired
    ShowRepository showRepository;
    

    @Nested
    class testBasicCRUD {

        @Test
         void testSaveArtist() {
            Artist artist = new Artist("artistName", "coverUrl", "contact", "webSite", "city", "bio", 3, 1, true);
            artist.setUserId(3);
            artist.setMusicalStyleId(1);
            assertTrue(artistRepository.save(artist));
            assertNotNull(artist.getId());
        }

        @Test
         void testFindArtist() {
            assertNotNull(artistRepository.findById(1));
            
        }

        @Test
        void testFindWIthPagination(){
            assertEquals(2, artistRepository.findAllPagineList(0).size());
        }

        @Test 
        void numberOfArtist(){
            assertEquals(2, artistRepository.numberOfArtist());
        }

        @Test
         void testFindAllArtist() {
            
            assertNotEquals(0,artistRepository.findAll().size());

        }

        @Test
         void testUpdateArtist() {
            Artist artist = new Artist("artistName", "coverUrl", "contact", "webSite", "city", "bio", 3, 1, true);
            artist.setUserId(1);
            artist.setMusicalStyleId(1);
            artist.setId(1);
            assertTrue(artistRepository.update(artist));
        }
        @Test
         void deleteArtistById(){
            assertTrue(artistRepository.deleteById(1));
        }
    }


    @Test
    void testFindAllHaveShow() {
        assertEquals(2, artistRepository.findAllHaveShow().size());
    }

    @Test
    void testFindAllSortedByListenCount() {
        List<Artist> list = artistRepository.findAllSortedByListenCount();
        assertTrue(list.get(0).getListenCount()>list.get(1).getListenCount());
    }

    @Test
    void testFindAllSortedByVotes() {
        List<Artist> list = artistRepository.findAllSortedByVotes();
        assertTrue(list.get(0).getVoteCount()>list.get(1).getVoteCount());
    }

    @Test
    void testFindByArtistName() {
        assertEquals(1, artistRepository.findByArtistName("Amen Ra").size());
    }

    @Test
    void testFindByCity() {
        assertEquals(1, artistRepository.findByCity("UK").size());
    }

    @Test
    void testFindByFavorites() {
        assertEquals(1, artistRepository.findByFavorites(1).size());
    }

    @Test
    void testFindByMusicalStyle() {
        assertEquals(1, artistRepository.findByMusicalStyle(1).size());
    }

    @Test
    void testFindByShowCity() {
        assertEquals(2, artistRepository.findByShowCity("test").size());
    }

    @Test
    void testFindByShowDate() {
        assertEquals(1, artistRepository.findByShowDate(LocalDate.of(2021, 01, 01)).size());
    }

    @Test
    void testFindByShowVenue() {
        assertEquals(2, artistRepository.findByShowVenue("venue").size());
    }

    @Test
    void testFindByUser() {
       assertNotNull(artistRepository.findByUserId(1));
    }

    @Test
    void testsaveShow(){
        Show show = new Show(LocalDate.of(2022, 01, 01), "venue", "adress");
        assertTrue(artistRepository.saveShow(1, show));
    }
}
