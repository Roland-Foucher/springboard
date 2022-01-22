package co.simplon.p16.springboard.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.stereotype.Controller;

import co.simplon.p16.springboard.entity.Artist;
import co.simplon.p16.springboard.entity.Track;

@Controller
public class TrackRepository extends GlobalRepository<Track> implements ITrackRepository {

    

    public TrackRepository() {
     this.findAllQuery = "SELECT * FROM tracks";
     this.findByIdQuery = "SELECT * FROM tracks WHERE id=?";
     this.saveQuery = "INSERT INTO tracks (name, url, artistId) VALUES(?,?,?)" ;
     this.updateQuery = "UPDATE tracks SET name=?, url=?, artistId=? WHERE id=?";
     this.deleteQuery = "DELETE FROM tracks WHERE id=?";
    }

    @Override
    public Track instanciateObject(ResultSet result) {
        try {
            return new Track(result.getInt("id"),result.getString("name"), result.getString("url"), result.getInt("artistId"));
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            System.out.println("error on instanciate object");
        }
        return null;
    }

    @Override
    public void injectParamatersToSaveStatement(Track track) {
        try {
            stmt.setString(1, track.getName());
            stmt.setString(2, track.getUrl());
            stmt.setInt(3, track.getArtistId());

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            System.out.println("error on inject parameters to save statement");
        }

    }

    @Override
    public void injectParamatersToUpdateStatement(Track track) {
        try {
            injectParamatersToSaveStatement(track);
            stmt.setInt(4, track.getId());
        } catch (SQLException e) {
            System.out.println("error on inject parameters to update statement");
            e.printStackTrace();
        }

    }

    @Override
    public void injectGeneratedKey(Track track, int generatedId) {
        track.setId(generatedId);

    }

    @Override
    public List<Track> findByArtistId(Integer artistId) {
        // TODO Auto-generated method stub
        return null;
    }



}
