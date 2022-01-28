package co.simplon.p16.springboard.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.util.List;

import javax.sql.DataSource;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.core.io.Resource;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.util.ResourceUtils;

import co.simplon.p16.springboard.entity.Artist;
import co.simplon.p16.springboard.entity.Show;


 class ArtistRepositoryTest {

    
    ArtistRepository artistRepository;
    SocialNetworkRepository socialNetworkRepository;
    TrackRepository trackRepository;
    ShowRepository showRepository;
    

    @BeforeEach
     void init() {
        artistRepository = new ArtistRepository();
        socialNetworkRepository = new SocialNetworkRepository();
        trackRepository = new TrackRepository();
        showRepository = new ShowRepository();

        artistRepository.setSocialNetworkRepository(socialNetworkRepository);
        artistRepository.setTrackRepository(trackRepository);
        artistRepository.setShowRepository(showRepository);
       
        DataSource dataSource = new EmbeddedDatabaseBuilder()
                .setType(EmbeddedDatabaseType.H2)
                .addScript("springboard-test.sql")
                .build();

        artistRepository.setDataSource(dataSource);
        socialNetworkRepository.setDataSource(artistRepository.getDataSource());
        trackRepository.setDataSource(artistRepository.getDataSource());
        showRepository.setDataSource(artistRepository.getDataSource());


    }

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
         void testFindAllArtist() {
            
            assertNotNull(artistRepository.findAll());

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
        assertEquals(1, artistRepository.findByArtistName("test").size());
    }

    @Test
    void testFindByCity() {
        assertEquals(1, artistRepository.findByCity("city").size());
    }

    @Test
    void testFindByFavorites() {
        assertEquals(1, artistRepository.findByFavorites(1).size());
    }

    @Test
    void testFindByMusicalStyle() {
        assertEquals(1, artistRepository.findByMusicalStyle(3).size());
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
