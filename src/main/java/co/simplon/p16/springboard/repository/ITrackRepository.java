package co.simplon.p16.springboard.repository;

import java.util.List;

import co.simplon.p16.springboard.entity.Track;

public interface ITrackRepository extends IGlobalRepository<Track> {

    List<Track> findByArtistId(Integer artistId);

    Integer deleteByArtistId(Integer artistId);

}
