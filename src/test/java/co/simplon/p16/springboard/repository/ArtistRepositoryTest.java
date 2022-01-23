package co.simplon.p16.springboard.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import javax.sql.DataSource;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.jdbc.DataJdbcTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.test.context.ActiveProfiles;

import co.simplon.p16.springboard.entity.Artist;


 class ArtistRepositoryTest {

    
    ArtistRepository artistRepository;
    SocialNetworkRepository socialNetworkRepository;
    TrackRepository trackRepository;
    

    @BeforeEach
     void init() {
        artistRepository = new ArtistRepository();
        socialNetworkRepository = new SocialNetworkRepository();
        trackRepository = new TrackRepository();
        artistRepository.setSocialNetworkRepository(socialNetworkRepository);
        artistRepository.setTrackRepository(trackRepository);
       
        DataSource dataSource = new EmbeddedDatabaseBuilder()
                .setType(EmbeddedDatabaseType.H2)
                .addScript("springboard-test.sql")
                .build();
        artistRepository.setDataSource(dataSource);
        socialNetworkRepository.setDataSource(artistRepository.getDataSource());
        trackRepository.setDataSource(artistRepository.getDataSource());


    }

    @Nested
    class testBasicCRUD {

        @Test
         void testSaveArtist() {
            Artist artist = new Artist("artistName", "coverUrl", "contact", "webSite", "city", "bio", 3, 1);
            artist.setUserId(1);
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
            Artist artist = new Artist("artistName", "coverUrl", "contact", "webSite", "city", "bio", 3, 1);
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

    }

    @Test
    void testFindAllSortedByListenCount() {

    }

    @Test
    void testFindAllSortedByVotes() {

    }

    @Test
    void testFindByArtistName() {

    }

    @Test
    void testFindByCity() {

    }

    @Test
    void testFindByFavorites() {

    }

    @Test
    void testFindByMusicalStyle() {

    }

    @Test
    void testFindByShowCity() {

    }

    @Test
    void testFindByShowDate() {

    }

    @Test
    void testFindByShowVenue() {

    }

    @Test
    void testFindByUser() {

    }
}
