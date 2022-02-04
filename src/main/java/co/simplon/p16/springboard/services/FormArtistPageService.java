package co.simplon.p16.springboard.services;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import co.simplon.p16.springboard.entity.Artist;
import co.simplon.p16.springboard.entity.SocialNetwork;
import co.simplon.p16.springboard.entity.Track;
import co.simplon.p16.springboard.entity.User;
import co.simplon.p16.springboard.repository.IArtistRepository;
import co.simplon.p16.springboard.repository.IMusicalStyleRepository;
import co.simplon.p16.springboard.repository.IShowRepository;
import co.simplon.p16.springboard.repository.ISocialNetworkRepository;
import co.simplon.p16.springboard.repository.ITrackRepository;
import co.simplon.p16.springboard.repository.TrackRepository;

@Service
public class FormArtistPageService {

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

    public Artist setListToUpdateArtistPage(User user) {

        Artist artist = artistRepository.findByUserId(user.getId());
        List<SocialNetwork> socialNetworks = socialNetworkRepository.findByArtistId(artist.getId());
        while (socialNetworks.size() < 4) {
            socialNetworks.add(new SocialNetwork());
        }
        List<Track> trackList = new ArrayList<>(
                List.of(new Track(), new Track(), new Track(), new Track()));

        artist.setSocialNetworkList(socialNetworks);
        artist.setTrackList(trackList);
        return artist;
    }

    public boolean saveArtistPage(Artist artist, List<String> urlAudioList, String urlCover, Integer userId) {
        boolean allOk = true;

        // save new artist
        artist.setCoverUrl(urlCover);
        artist.setListenCount(0);
        artist.setVoteCount(0);
        artist.setUserId(userId);
        if (!artistRepository.save(artist)) {
            allOk = false;
        }

        allOk = saveTrackList(artist, urlAudioList);
        allOk = saveSocialNetwork(artist);
        return allOk;
    }

    public boolean updateArtistPage(Artist artist, List<String> urlAudioList, String urlCover, Integer userId) {
        boolean AllOk = true;
        Artist oldArtist = artistRepository.findById(artist.getId());
        // save new artist
        artist.setCoverUrl(urlCover);
        artist.setListenCount(oldArtist.getListenCount());
        artist.setVoteCount(oldArtist.getVoteCount());
        artist.setUserId(userId);
        if (!artistRepository.update(artist)) {
            AllOk = false;
        }

        // delete old tracks in datablase
        trackRepository.deleteByArtistId(artist.getId());
        // save trackList
        AllOk = saveTrackList(artist, urlAudioList);
        // delete old social Networks
        socialNetworkRepository.deleteByArtistId(artist.getId());
        // save social Networks
        AllOk = saveSocialNetwork(artist);

        return AllOk;

    }

    public boolean saveTrackList(Artist artist, List<String> urlAudioList) {
        // save trackList
        List<Track> trackList = artist.getTrackList();

        for (int i = 0; i < urlAudioList.size(); i++) {
            trackList.get(i).setUrl(urlAudioList.get(i));
            trackList.get(i).setArtistId(artist.getId());
            if (!trackRepository.save(trackList.get(i))) {
                return false;
            }
        }
        return true;
    }

    public boolean saveSocialNetwork(Artist artist) {
        // save social Networks
        List<SocialNetwork> socialNetworksList = artist.getSocialNetworkList();
        for (SocialNetwork socialNetwork : socialNetworksList) {
            if (!socialNetwork.getName().isEmpty() || !socialNetwork.getUrl().isEmpty()) {
                socialNetwork.setArtistId(artist.getId());
                if (!socialNetworkRepository.save(socialNetwork)) {
                    return false;
                }
            }
        }
        return true;
    }

    public String updateCoverFile(boolean modifyCover, MultipartFile coverFile, Artist artist) throws IOException {

        if (modifyCover) {
            String oldCoverUrl = artistRepository.findById(artist.getId()).getCoverUrl();
            uploadFile.deleteFile(oldCoverUrl);
            if (uploadFile.checkFile(coverFile, "^image/.*")) {
                return uploadFile.saveFile(coverFile, artist);
            }
            return null;
        } else {
            return artistRepository.findById(artist.getId()).getCoverUrl();
        }
    }

    public List<String> updateAudioFiles(boolean modifyTracks, MultipartFile[] audioFiles, Artist artist)
            throws IOException {

        if (modifyTracks) {
            // delete old files
            List<Track> oldTrackList = trackRepository.findByArtistId(artist.getId());
            for (Track track : oldTrackList) {
                uploadFile.deleteFile(track.getUrl());
            }

            // save new files
            List<String> urlAudioFileList = new ArrayList<>();
            for (MultipartFile file : audioFiles) {
                if (uploadFile.checkFile(file, "^audio/.*")) {
                    urlAudioFileList.add(uploadFile.saveFile(file, artist));
                }
            }
            return urlAudioFileList;

        } else {
            List<String> audioFilesUrlList = new ArrayList<>();
            trackRepository.findByArtistId(artist.getId()).forEach((el) -> {
                audioFilesUrlList.add(el.getUrl());
            });
            return audioFilesUrlList;
        }
    }
}
