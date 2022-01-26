package co.simplon.p16.springboard.services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.simplon.p16.springboard.entity.Artist;
import co.simplon.p16.springboard.entity.MusicalStyle;
import co.simplon.p16.springboard.repository.ArtistRepository;
import co.simplon.p16.springboard.repository.IArtistRepository;
import co.simplon.p16.springboard.repository.IMusicalStyleRepository;
import co.simplon.p16.springboard.repository.IShowRepository;
import co.simplon.p16.springboard.repository.ISocialNetworkRepository;
import co.simplon.p16.springboard.repository.MusicalStyleRepository;
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
    TrackRepository trackRepository;

    
    public List<Artist> display10ShuffleArtistsCards(){
        List<Artist> artistsList = artistRepository.findAll();
        artistsList.forEach((el) -> {
            el.setStyleName(musicalStyleRepository.findById(el.getMusicalStyleId()).getStyle());
        });
        Collections.shuffle(artistsList);
        List <Artist> tenArtistsList = new ArrayList<>();
        int artistsInList = artistsList.size()<9 ? artistsList.size() : 8;
        for(int i=0; i<artistsInList; i++){
            tenArtistsList.add(artistsList.get(i));
        }
       
        
        return tenArtistsList;
    }

    public Artist displayArtistPage(Integer id){
        Artist artist = artistRepository.findById(id);
        artist.setStyleName(musicalStyleRepository.findById(artist.getId()).getStyle());
        artist.setShowList(showRepository.findByArtist(id));
        artist.setSocialNetworkList(socialNetworkRepository.findByArtistId(id));
        artist.setTrackList(trackRepository.findByArtistId(id));

        return artist;
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
