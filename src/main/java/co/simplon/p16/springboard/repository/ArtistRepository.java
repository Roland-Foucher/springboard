package co.simplon.p16.springboard.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import co.simplon.p16.springboard.entity.Artist;
import co.simplon.p16.springboard.entity.Show;

@Repository
public class ArtistRepository extends GlobalRepository<Artist> implements IArtistRepository {

    @Autowired
    private ShowRepository showRepository;

    //
    // Define specifics query
    //

    private final String findByUserIdQuery = "SELECT * FROM artists WHERE userId=?";
    private final String findAllSortedByVoteQuery = "SELECT * FROM artists ORDER BY voteCount DESC";
    private final String findAllSortedByLisenCountQuery = "SELECT * FROM artists ORDER BY listenCount DESC";
    private final String findByArtistNameQuery = "SELECT * FROM artists WHERE artistName = ?";
    private final String findByCityQuery = "SELECT * FROM artists WHERE city = ?";

    // query to find artists have show without duplicated result
    private final String findAllHaveShowQuery = """
            SELECT DISTINCT a.*
            FROM artists AS a, artistsShows AS as
            WHERE a.id = as.artistId""";

    // query to find artists linked to favorits table with artists id
    private final String findByUpVotesQuery = """
            SELECT a.*
            FROM artists AS a, upVotes AS u
            WHERE u.userId=? AND u.artistId = a.id""";

    // query to find artists linked to upVotes table with artists id
    private final String findByFavoritsQuery = """
            SELECT a.*
            FROM artists AS a, favoritsArtists AS fa
            WHERE fa.userId=? AND fa.artistId = a.id""";

    // querty to find artits linked to musicalStyle table with the musical id
    private final String findByMusicalStyleQuery = """
            SELECT a.*
            FROM artists AS a, musicalStyle AS m
            WHERE a.musicalStyleId = ? AND m.Id = a.musicalStyleId""";

    // querty to find artits linked to show table with the adress of show without
    // duplicated result
    private final String findByShowAdressQuery = """
            SELECT DISTINCT a.*
            FROM artists AS a, artistsShows AS ash, shows AS s
            WHERE s.id = ash.showId AND ash.artistId = a.id AND s.adress = ?""";

    // querty to find artits linked to show table with the venue of show without
    // duplicated result
    private final String findByShowVenueQuery = """
            SELECT DISTINCT artists.*
            FROM artists, artistsShows, shows
            WHERE shows.id = artistsShows.showId AND artistsShows.artistId = artists.id AND shows.venue = ?""";

    // querty to find artits linked to show table after a specific date of show
    // without duplicated result
    private final String findByShowDateQuery = """
            SELECT DISTINCT artists.*
            FROM artists, artistsShows, shows
            WHERE shows.id = artistsShows.showId AND artistsShows.artistId = artists.id AND shows.date > ?""";


    private final String saveShowQuery = "INSERT INTO artistsShows VALUES (?,?)";

    // define query from global repository
    public ArtistRepository() {
        this.findAllQuery = "SELECT * FROM artists";
        this.findByIdQuery = "SELECT * FROM artists WHERE id=?";
        this.saveQuery = """
                INSERT INTO artists
                (artistName, coverUrl, contact, webSite, city, voteCount, bio, listenCount, isOnArtistList, musicalStyleId, UserId)
                VALUES(?,?,?,?,?,?,?,?,?,?,?)""";
        this.updateQuery = """
                UPDATE artists
                SET artistName=?, coverUrl=?, contact=?, webSite=?, city=?,
                voteCount=?, bio=?, listenCount=?,isOnArtistList=?, musicalStyleId=?, userId = ?
                WHERE id=?""";
        this.deleteQuery = "DELETE FROM artists WHERE id=?";
    }

    //
    // Override GlobalRepository methodes
    //

    @Override
    protected void injectGeneratedKey(Artist artist, int generatedId) {
        artist.setId(generatedId);
    }

    @Override
    protected void injectParamatersToSaveStatement(Artist artist) throws SQLException {

        stmt.setString(1, artist.getArtistName());
        stmt.setString(2, artist.getCoverUrl());
        stmt.setString(3, artist.getContact());
        stmt.setString(4, artist.getWebSite());
        stmt.setString(5, artist.getCity());
        stmt.setInt(6, artist.getVoteCount());
        stmt.setString(7, artist.getBio());
        stmt.setInt(8, artist.getListenCount());
        stmt.setBoolean(9, artist.getIsOnArtistList());
        stmt.setInt(10, artist.getMusicalStyleId());
        stmt.setInt(11, artist.getUserId());
    }

    @Override
    protected void injectParamatersToUpdateStatement(Artist artist) throws SQLException {

        injectParamatersToSaveStatement(artist);
        stmt.setInt(12, artist.getId());
    }

    @Override
    protected Artist instanciateObject(ResultSet result) throws SQLException {

        return new Artist(result.getInt("id"),
                result.getString("artistName"),
                result.getString("coverUrl"),
                result.getString("contact"),
                result.getString("webSite"),
                result.getString("city"),
                result.getString("bio"),
                result.getInt("listenCount"),
                result.getInt("voteCount"),
                result.getBoolean("isOnArtistList"),
                result.getInt("musicalStyleId"),
                result.getInt("userId"));
    }

    //
    // Add specifics methods
    //

    @Override
    public List<Artist> findAllSortedByVotes() {

        return super.findAllWithParamQuery(findAllSortedByVoteQuery);
    }

    @Override
    public List<Artist> findAllSortedByListenCount() {

        return super.findAllWithParamQuery(findAllSortedByLisenCountQuery);
    }

    @Override
    public List<Artist> findAllHaveShow() {

        return super.findAllWithParamQuery(findAllHaveShowQuery);
    }

    @Override
    public List<Artist> findByArtistName(String artistName) {

        return super.findListByString(artistName, findByArtistNameQuery);
    }

    @Override
    public List<Artist> findByCity(String city) {

        return super.findListByString(city, findByCityQuery);
    }

    @Override
    public List<Artist> findByMusicalStyle(Integer styleId) {

        return super.findListByInteger(styleId, findByMusicalStyleQuery);
    }

    @Override
    public List<Artist> findByShowCity(String adress) {

        return super.findListByString(adress, findByShowAdressQuery);

    }

    @Override
    public List<Artist> findByShowVenue(String showVenue) {

        return super.findListByString(showVenue, findByShowVenueQuery);

    }

    /**
     * Find by show date after the specified date
     */
    @Override
    public List<Artist> findByShowDate(LocalDate date) {
        return super.findListByDate(date, findByShowDateQuery);
    }

    @Override
    public Artist findByUserId(Integer userId) {
        return super.findOneByInteger(userId, findByUserIdQuery);
    }

    @Override
    public List<Artist> findByFavorites(Integer userId) {

        return super.findListByInteger(userId, findByFavoritsQuery);
    }

    @Override
    public List<Artist> findByUpVotes(Integer userId) {
        return super.findListByInteger(userId, findByUpVotesQuery);
    }

    /**
     * methode to add a user show in database. call shwoRepository methode save()
     * 
     * @param artistId id of artist that have this show
     * @param show     Show entity to add in database
     * @return globalRepository methode saveOrDeleteOnManyToManyTable() to
     *         implements junction table.
     */
    @Override
    public boolean saveShow(Integer artistId, Show show) {
        showRepository.save(show);
        return super.saveOrDeleteOnManyToManyTable(artistId, show.getId(), saveShowQuery);

    }

    //
    // GETTER AND SETTERS
    //

    public ShowRepository getShowRepository() {
        return showRepository;
    }

    public void setShowRepository(ShowRepository showRepository) {
        this.showRepository = showRepository;
    }

}
