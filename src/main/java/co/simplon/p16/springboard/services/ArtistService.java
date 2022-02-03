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


    public List<Artist> display10ShuffleArtistsCards() {
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

    public List<Artist> displayFavoritArtistCards(Integer userId) {
        List<Artist> artistsList = artistRepository.findByFavorites(userId);
        artistsList.forEach((el) -> {
            el.setStyleName(musicalStyleRepository.findById(el.getMusicalStyleId()).getStyle());
        });

        return artistsList;
    }

    public Artist displayArtistPage(Integer id) {
        Artist artist = artistRepository.findById(id);
        artist.setStyleName(musicalStyleRepository.findById(artist.getMusicalStyleId()).getStyle());
        artist.setShowList(showRepository.findByArtist(id));
        artist.setSocialNetworkList(socialNetworkRepository.findByArtistId(id));
        artist.setTrackList(trackRepository.findByArtistId(id));
        return artist;
    }

    public boolean deleteArtistPage(Authentication authentication){
        User user = (User) authentication.getPrincipal();
        Artist artist = artistRepository.findByUserId(user.getId());
        List<Track> tracks = trackRepository.findByArtistId(artist.getId());
        if (artistRepository.deleteById(artist.getId())){
            uploadFile.deleteFile(artist.getCoverUrl());
            for (Track track : tracks) {
                uploadFile.deleteFile(track.getUrl());
            }
            return true;
        }
        return false;
    }

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
