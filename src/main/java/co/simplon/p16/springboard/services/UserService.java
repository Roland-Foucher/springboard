package co.simplon.p16.springboard.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import co.simplon.p16.springboard.entity.Artist;
import co.simplon.p16.springboard.entity.User;
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

    public void setNewRole(String role, Authentication authentication){
         //update user role in database
        User user = (User) authentication.getPrincipal();
        user.setRole(role);
        userRepository.update(user);
        
         //update user role connected to don't logout and re-login
        List<GrantedAuthority> updatedAuthorities = new ArrayList<>(user.getAuthorities());
        updatedAuthorities.add(new SimpleGrantedAuthority(role));
        Authentication newAuth = new UsernamePasswordAuthenticationToken(authentication.getPrincipal(), authentication.getCredentials(), updatedAuthorities);
        SecurityContextHolder.getContext().setAuthentication(newAuth);
    }
    
}
