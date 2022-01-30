package co.simplon.p16.springboard.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.simplon.p16.springboard.entity.Artist;
import co.simplon.p16.springboard.repository.IArtistRepository;
import co.simplon.p16.springboard.repository.IUserRepository;

@Service
public class UserService {

    @Autowired
    IUserRepository userRepository;

    @Autowired
    IArtistRepository artistRepository;

    public boolean takeFavoritArtist(int userId, int artistId) {

        if (checkIfArtistIsFavorite(userId, artistId)) {
            return userRepository.deleteSingleFavoriteArtist(userId, artistId);
        }
        return userRepository.setFavoriteArtist(userId, artistId);
    }

    public boolean takeUpVoteToArtist(int userId, int artistId) {
        Artist artist = artistRepository.findById(artistId);
        if (checkIfArtistIsUpvoted(userId, artistId)) {

            artist.takeUpVote(-1);
            artistRepository.update(artist);
            return userRepository.deleteSingleUpvote(userId, artistId);
        }

        artist.takeUpVote(1);
        artistRepository.update(artist);
        return userRepository.setUpvote(userId, artistId);
    }



    public boolean checkIfArtistIsUpvoted(int userId, int artistId) {
        List<Artist> upVoteArtists = artistRepository.findByUpVotes(userId);
        for (Artist artist : upVoteArtists) {
            if (artist.getId() == artistId) {
                return true;
            }
        }
        return false;
    }

    public boolean checkIfArtistIsFavorite(int userId, int artistId) {
        List<Artist> favoritArtists = artistRepository.findByFavorites(userId);
        for (Artist artist : favoritArtists) {
            if (artist.getId() == artistId) {
                return true;
            }
        }
        return false;
    }
}
