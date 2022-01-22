package co.simplon.p16.springboard.repository;

import java.sql.ResultSet;
import java.util.List;

import co.simplon.p16.springboard.entity.Show;

public interface IShowRepository extends IGlobalRepository<Show> {
    List <Show> findByArtist(Integer artistId);


}
