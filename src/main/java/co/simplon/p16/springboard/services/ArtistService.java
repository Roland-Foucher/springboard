package co.simplon.p16.springboard.services;

import java.util.ArrayList;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.simplon.p16.springboard.entity.Artist;

import co.simplon.p16.springboard.entity.SocialNetwork;
import co.simplon.p16.springboard.entity.Track;

import co.simplon.p16.springboard.repository.IArtistRepository;
import co.simplon.p16.springboard.repository.IMusicalStyleRepository;
import co.simplon.p16.springboard.repository.IShowRepository;
import co.simplon.p16.springboard.repository.ISocialNetworkRepository;

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
    
    public Artist setListInNewArtist() {
        Artist artist = new Artist();
        List<SocialNetwork> socialNetworks = new ArrayList<>(
                List.of(new SocialNetwork(), new SocialNetwork(), new SocialNetwork(), new SocialNetwork()));
        List<Track> trackList = new ArrayList<>(
                List.of(new Track(), new Track(), new Track(), new Track()));

        artist.setSocialNetworkList(socialNetworks);
        artist.setTrackList(trackList);
        return artist;
    }

    public Artist setListToUpdateArtistPage(int id) {
        Artist artist = artistRepository.findById(id);
        List<SocialNetwork> socialNetworks = socialNetworkRepository.findByArtistId(id);
        while (socialNetworks.size() < 4) {
            socialNetworks.add(new SocialNetwork());
        }
        List<Track> trackList = trackRepository.findByArtistId(id);
        while (trackList.size() < 4) {
            trackList.add(new Track());
        }
        artist.setSocialNetworkList(socialNetworks);
        artist.setTrackList(trackList);
        return artist;
    }

    public boolean saveArtistPage(Artist artist, List<String> urlList, Integer userId) {
        artist.setCoverUrl(urlList.get(urlList.size() - 1));
        artist.setListenCount(0);
        artist.setVoteCount(0);
        artist.setUserId(userId);

        boolean AllOk = true;

        List<Track> trackList = artist.getTrackList();
        if (!artistRepository.save(artist)) {
            AllOk = false;
        }
        for (int i = 0; i < urlList.size() - 1; i++) {

            trackList.get(i).setUrl(urlList.get(i));
            trackList.get(i).setArtistId(artist.getId());
            if (!trackRepository.save(trackList.get(i))) {
                AllOk = false;
            }
        }
        List<SocialNetwork> socialNetworksList = artist.getSocialNetworkList();
        for (SocialNetwork socialNetwork : socialNetworksList) {
            if (!socialNetwork.getName().equals("") || !socialNetwork.getUrl().equals("")) {
                socialNetwork.setArtistId(artist.getId());
                if (!socialNetworkRepository.save(socialNetwork)) {
                    AllOk = false;
                }
            }
        }

        return AllOk;

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
