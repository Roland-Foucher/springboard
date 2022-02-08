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

    private final String imgPattern = "^image/.*";
    private final String audioPattern = "^audio/.*";

    /**
     * methode use with empty form, new artist page
     * inject empty socialNetworks and tracks to artist to make a new artist page
     */
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

    /**
     * methode use for update artist page.
     * 
     * @param user connected user to update artsit
     * @return artist whith this socialNetworks and tracks
     */
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

    /**
     * after submit save form and save files in server
     * methode to save all artist attributs in database
     * 
     * @param artist artist to save in database
     * @param urlAudioList path of all audio files in server
     * @param urlCover path of cover file in server
     * @param userId user connected (linked to artist)
     * @return boolean allOk, check all step of the method
     */
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

    /**
     * after submit update form and update files in server
     * methode to update artist in database. delete old track and socialnetorks in database before save them
     * 
     * @param artist artist to save in database
     * @param urlAudioList path of all audio files in server
     * @param urlCover path of cover file in server
     * @param userId user connected (linked to artist)
     * @return boolean allOk, check all step of the method
     */
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

    /**
     * save all tracks in database
     * @param artist artist to link id
     * @param urlAudioList list of path track
     * @return true if all track are saved in database
     */
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

    /**
     * save all socialNetworks in database
     * @param artist artist to link id
     * @return true if all socialNetworks are saved in database
     */
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
    /**
     * methode to update coverFile in server if the file was modified in the update form
     * Delete old Cover before save the new Cover if modifyCover is true or return old cover url.
     * @param modifyCover the boolean return in the form => cover was change or not
     * @param coverFile new coverFile (null if not changed)
     * @param artist artist that update his page
     * @return path of the vcover file in server
     * @throws IOException on save file maybe exception when save in server
     */
    public String updateCoverFile(boolean modifyCover, MultipartFile coverFile, Artist artist) throws IOException {

        if (modifyCover) {
            String oldCoverUrl = artistRepository.findById(artist.getId()).getCoverUrl();
            uploadFile.deleteFile(oldCoverUrl);
            if (uploadFile.checkFile(coverFile, imgPattern)) {
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
                if (uploadFile.checkFile(file, audioPattern)) {
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
