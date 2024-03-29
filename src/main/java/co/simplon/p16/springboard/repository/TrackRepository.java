package co.simplon.p16.springboard.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.stereotype.Repository;

import co.simplon.p16.springboard.entity.Track;

@Repository
public class TrackRepository extends GlobalRepository<Track> implements ITrackRepository {

    // Define specifics query
    private final String deleteByArtistQuery = "DELETE FROM tracks WHERE artistId=?";
    private final String findByArtistIdQuery = "SELECT * FROM tracks WHERE artistId=?";

    // define query from global repository
    public TrackRepository() {
        this.findAllQuery = "SELECT * FROM tracks";
        this.findByIdQuery = "SELECT * FROM tracks WHERE id=?";
        this.saveQuery = "INSERT INTO tracks (name, url, artistId) VALUES(?,?,?)";
        this.updateQuery = "UPDATE tracks SET name=?, url=?, artistId=? WHERE id=?";
        this.deleteQuery = "DELETE FROM tracks WHERE id=?";
    }

    //
    // Override GlobalRepository methodes
    //

    @Override
    protected Track instanciateObject(ResultSet result) throws SQLException {

        return new Track(result.getInt("id"), result.getString("name"), result.getString("url"),
                result.getInt("artistId"));

    }

    @Override
    protected void injectParamatersToSaveStatement(Track track) throws SQLException {

        stmt.setString(1, track.getName());
        stmt.setString(2, track.getUrl());
        stmt.setInt(3, track.getArtistId());
    }

    @Override
    protected void injectParamatersToUpdateStatement(Track track) throws SQLException {

        injectParamatersToSaveStatement(track);
        stmt.setInt(4, track.getId());
    }

    @Override
    protected void injectGeneratedKey(Track track, int generatedId) {
        track.setId(generatedId);

    }

    //
    // Add specifics methods
    //

    @Override
    public List<Track> findByArtistId(Integer artistId) {

        return super.findListByInteger(artistId, findByArtistIdQuery);
    }

    @Override
    public Integer deleteByArtistId(Integer artistId) {

        return super.deleteByInteger(artistId, deleteByArtistQuery);
    }

}
