package co.simplon.p16.springboard.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.mock;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import co.simplon.p16.springboard.entity.Artist;
import co.simplon.p16.springboard.entity.MusicalStyle;
import co.simplon.p16.springboard.entity.Show;
import co.simplon.p16.springboard.entity.SocialNetwork;
import co.simplon.p16.springboard.entity.Track;
import co.simplon.p16.springboard.repository.ArtistRepository;
import co.simplon.p16.springboard.repository.MusicalStyleRepository;
import co.simplon.p16.springboard.repository.ShowRepository;
import co.simplon.p16.springboard.repository.SocialNetworkRepository;
import co.simplon.p16.springboard.repository.TrackRepository;

public class ArtistServiceTest {

    ArtistService artistService;
    MusicalStyle musicalStyle;
    Artist artist;
    List<Show> showList;
    List<SocialNetwork> socialNetworksList;
    List<Artist> artistsList;
    List<Track> trackList;


    ArtistRepository artistRepository;
    MusicalStyleRepository musicalStyleRepository;
    SocialNetworkRepository socialNetworkRepository;
    ShowRepository showRepository;
    TrackRepository trackRepository;

    @BeforeEach
    public void init() {
        artist = new Artist(1, "artistName", "coverUrl", "contact", "webSite", "city", "bio", 0, 0, true, 1, 1);
        artistsList = new ArrayList<>(List.of(
                new Artist(1, "artistName", "coverUrl", " contact", "webSite", "city", "bio", 1, 1,true, 1, 1),
                new Artist(2, "artistName", "coverUrl", " contact", "webSite", "city", "bio", 1, 1,true, 1, 2)));

        musicalStyle = new MusicalStyle(1, "rock");
        showList = new ArrayList<>(List.of(new Show(LocalDate.of(2021, 10, 10), "venue", "adress")));
        socialNetworksList = new ArrayList<>(List.of(new SocialNetwork("url", "name"), new SocialNetwork("url2","name2")));
        trackList = new ArrayList<>(List.of(new Track("name", "url")));

        artistService = new ArtistService();

        artistRepository = mock(ArtistRepository.class);
        musicalStyleRepository = mock(MusicalStyleRepository.class);
        socialNetworkRepository = mock(SocialNetworkRepository.class);
        showRepository = mock(ShowRepository.class);
        trackRepository = mock(TrackRepository.class);

        artistService.setArtistRepository(artistRepository);
        artistService.setMusicalStyleRepository(musicalStyleRepository);
        artistService.setShowRepository(showRepository);
        artistService.setSocialNetworkRepository(socialNetworkRepository);
        artistService.setTrackRepository(trackRepository);

    }

    @Test
    void testShuffleArtistCards() {
        when(artistRepository.findAll()).thenReturn(artistsList);
        when(musicalStyleRepository.findById(anyInt())).thenReturn(musicalStyle);
        List<Artist> list = artistService.display10ShuffleArtistsCards();
        assertEquals("rock", list.get(0).getStyleName());
        

    }

    @Test
    void testDisplayArtistPage() {
        when(artistRepository.findById(1)).thenReturn(artist);
        when(musicalStyleRepository.findById(1)).thenReturn(musicalStyle);
        when(socialNetworkRepository.findByArtistId(1)).thenReturn(socialNetworksList);
        when(showRepository.findByArtist(1)).thenReturn(showList);
        when(trackRepository.findByArtistId(1)).thenReturn(trackList);

        artist = artistService.displayArtistPage(1);
        assertEquals("rock", artist.getStyleName());
        assertEquals(2, artist.getSocialNetworkList().size());
        assertEquals(1, artist.getShowList().size());
        assertEquals(1, artist.getTrackList().size());

    }

    @Test
    void testSaveArtistPage(){
        
        
    }
}
