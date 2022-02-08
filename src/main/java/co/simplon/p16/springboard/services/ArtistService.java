package co.simplon.p16.springboard.services;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import co.simplon.p16.springboard.entity.Artist;
import co.simplon.p16.springboard.entity.Track;
import co.simplon.p16.springboard.entity.User;
import co.simplon.p16.springboard.repository.IArtistRepository;
import co.simplon.p16.springboard.repository.IMusicalStyleRepository;
import co.simplon.p16.springboard.repository.IShowRepository;
import co.simplon.p16.springboard.repository.ISocialNetworkRepository;
import co.simplon.p16.springboard.repository.ITrackRepository;
import co.simplon.p16.springboard.repository.TrackRepository;

@Service
public class ArtistService {

    @Autowired
    IArtistRepository artistRepository;
    @Autowired
    IMusicalStyleRepository musicalStyleRepository;
    @Autowired
    IShowRepository showRepository;
    @Autowired
    ISocialNetworkRepository socialNetworkRepository;
    @Autowired
    ITrackRepository trackRepository;
    @Autowired
    UploadFile uploadFile;

    /**
     * display 8 random Artists cards in index page.
     * add style in all artists.
     * 
     * @return list of 8 artists
     */
    public List<Artist> display8ShuffleArtistsCards() {
        List<Artist> artistsList = artistRepository.findAll();
        artistsList.forEach((el) -> {
            el.setStyleName(musicalStyleRepository.findById(el.getMusicalStyleId()).getStyle());
        });
        Collections.shuffle(artistsList);
        List<Artist> tenArtistsList = new ArrayList<>();
        int artistsInList = artistsList.size() < 9 ? artistsList.size() : 8;
        for (int i = 0; i < artistsInList; i++) {
            tenArtistsList.add(artistsList.get(i));
        }

        return tenArtistsList;
    }

    /**
     * select favorits artists to display them in user account page.
     * inject styles in artists
     * @param userId user id connected
     * @return list of favorits artists
     */
    public List<Artist> displayFavoritArtistCards(Integer userId) {
        List<Artist> artistsList = artistRepository.findByFavorites(userId);
        artistsList.forEach((el) -> {
            el.setStyleName(musicalStyleRepository.findById(el.getMusicalStyleId()).getStyle());
        });

        return artistsList;
    }

    /**
     * add all attributs to artist - styleName, showList, socialNetworks, trackList
     * add a listen count when loading page
     * @param id artist id to display page
     * @return artist with all attributs
     */
    public Artist displayArtistPage(Integer id) {
        Artist artist = artistRepository.findById(id);
        artist.setStyleName(musicalStyleRepository.findById(artist.getMusicalStyleId()).getStyle());
        artist.setShowList(showRepository.findByArtist(id));
        artist.setSocialNetworkList(socialNetworkRepository.findByArtistId(id));
        artist.setTrackList(trackRepository.findByArtistId(id));
        return artist;
    }

    /**
     * delete artist and files that linked to artist
     * @param authentication to get user connected
     * @return true if artist is deleted false, if not
     */
    public boolean deleteArtistPage(Authentication authentication) {
        User user = (User) authentication.getPrincipal();
        Artist artist = artistRepository.findByUserId(user.getId());
        List<Track> tracks = trackRepository.findByArtistId(artist.getId());
        if (artistRepository.deleteById(artist.getId())) {
            uploadFile.deleteFile(artist.getCoverUrl());
            for (Track track : tracks) {
                uploadFile.deleteFile(track.getUrl());
            }
            return true;
        }
        return false;
    }

    public boolean addListenCount(Integer id){
        Artist artist = artistRepository.findById(id);
        artist.addListenCount();
        return artistRepository.update(artist);
    }
    //
    // getter and setter test
    //
    protected void setArtistRepository(IArtistRepository artistRepository) {
        this.artistRepository = artistRepository;
    }

    protected void setMusicalStyleRepository(IMusicalStyleRepository musicalStyleRepository) {
        this.musicalStyleRepository = musicalStyleRepository;
    }

    protected void setShowRepository(IShowRepository showRepository) {
        this.showRepository = showRepository;
    }

    protected void setSocialNetworkRepository(ISocialNetworkRepository socialNetworkRepository) {
        this.socialNetworkRepository = socialNetworkRepository;
    }

    public void setTrackRepository(TrackRepository trackRepository) {
        this.trackRepository = trackRepository;
    }

}
