package co.simplon.p16.springboard.repository;

import java.sql.ResultSet;
import java.util.List;

import co.simplon.p16.springboard.entity.SocialNetwork;

public interface ISocialNetworkRepository extends IGlobalRepository<SocialNetwork> {
    
    Integer deleteByArtistId(Integer artistId);
    List<SocialNetwork> findByArtistId(Integer artistId);
}
