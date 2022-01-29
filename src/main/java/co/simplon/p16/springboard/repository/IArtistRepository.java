package co.simplon.p16.springboard.repository;

import java.time.LocalDate;
import java.util.List;

import co.simplon.p16.springboard.entity.Artist;
import co.simplon.p16.springboard.entity.Show;

public interface IArtistRepository extends IGlobalRepository<Artist> {
    List<Artist> findAllSortedByVotes();

    List<Artist> findAllSortedByListenCount();

    List<Artist> findAllHaveShow();

    List<Artist> findByArtistName(String ArtistName);

    List<Artist> findByCity(String city);

    List<Artist> findByMusicalStyle(Integer styleId);

    List<Artist> findByShowCity(String showCity);

    List<Artist> findByShowVenue(String showVenue);

    List<Artist> findByShowDate(LocalDate date);

    Artist findByUserId(Integer userId);

    List<Artist> findByFavorites(Integer userId);

    List<Artist> findByUpVotes(Integer userId);

    boolean saveShow(Integer artistId, Show show);

}
