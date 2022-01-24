package co.simplon.p16.springboard.repository;

import co.simplon.p16.springboard.entity.User;

public interface IUserRepository extends IGlobalRepository<User> {
    User findByEmail(String email);

    boolean setUpvote(Integer artistId, Integer userId);

    Integer deleteAllUserUpvote(Integer userId);

    boolean setFavoriteArtist(Integer artistId, Integer userId);

    boolean deleteSingleFavoriteArtist(Integer userId, Integer artistId);

    Integer deleteAllFavoriteArtist(Integer userId);

}
