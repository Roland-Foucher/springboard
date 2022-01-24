package co.simplon.p16.springboard.repository;

import java.time.LocalDate;
import java.util.List;

import co.simplon.p16.springboard.entity.Show;

public interface IShowRepository extends IGlobalRepository<Show> {
    List<Show> findByArtist(Integer artistId);

    List<Show> findByAdress(String adress);

    List<Show> findByVenue(String venue);

    List<Show> findByDate(LocalDate date);

}
