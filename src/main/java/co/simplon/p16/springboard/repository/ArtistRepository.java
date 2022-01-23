package co.simplon.p16.springboard.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.stereotype.Repository;

import co.simplon.p16.springboard.entity.Artist;

@Repository
public class ArtistRepository extends GlobalRepository<Artist> implements IArtistRepository {

    @Autowired
    private SocialNetworkRepository socialNetworkRepository;
    @Autowired
    private TrackRepository trackRepository;
    
    
    private final String findByUserId =  "SELECT * FROM artists WHERE userId=?";

    public ArtistRepository() {
        this.findAllQuery = "SELECT * FROM artists";
        this.findByIdQuery = "SELECT * FROM artists WHERE id=?";
        this.saveQuery = "INSERT INTO artists (artistName, coverUrl, contact, webSite, city, voteCount, bio, listenCount, musicalStyleId, UserId) VALUES(?,?,?,?,?,?,?,?,?,?)";
        this.updateQuery = "UPDATE artists SET artistName=?, coverUrl=?, contact=?, webSite=?, city=?, voteCount=?, bio=?, listenCount=?, musicalStyleId=?, userId = ? WHERE id=?";
        this.deleteQuery = "DELETE FROM artists WHERE id=?";
    }

    //
    // add values for findAll, findById, save, update, delete
    //

    @Override
    protected void injectGeneratedKey(Artist artist, int generatedId) {
        artist.setId(generatedId);
    }

    @Override
    protected void injectParamatersToSaveStatement(Artist artist) {
        try {
            stmt.setString(1, artist.getArtistName());
            stmt.setString(2, artist.getCoverUrl());
            stmt.setString(3, artist.getContact());
            stmt.setString(4, artist.getWebSite());
            stmt.setString(5, artist.getCity());
            stmt.setInt(6, artist.getVoteCount());
            stmt.setString(7, artist.getBio());
            stmt.setInt(8, artist.getListenCount());
            stmt.setInt(9, artist.getMusicalStyleId());
            stmt.setInt(10, artist.getUserId());
        
        } catch (SQLException e) {
            System.out.println("error on inject parameters on statement for save artist");
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @Override
    protected void injectParamatersToUpdateStatement(Artist artist) {
        
        try {
            injectParamatersToSaveStatement(artist);
            stmt.setInt(11, artist.getId());
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            System.out.println("error on inject parameters in statement for update artist");
            e.printStackTrace();
        }
    }

    @Override
    protected Artist instanciateObject(ResultSet result) {

        try {
            return new Artist(result.getInt("id"),
                    result.getString("artistName"),
                    result.getString("coverUrl"),
                    result.getString("contact"),
                    result.getString("webSite"),
                    result.getString("city"),
                    result.getString("bio"),
                    result.getInt("listenCount"),
                    result.getInt("voteCount"),
                    result.getInt("musicalStyleId"),
                    result.getInt("userId"));
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            System.out.println("error on instanciate Artist Object");
            e.printStackTrace();
        }
        return null;
    }


    @Override
    public List<Artist> findAllSortedByVotes() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<Artist> findAllSortedByListenCount() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<Artist> findAllHaveShow(Integer styleId) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<Artist> findByArtistName(String ArtistName) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<Artist> findByCity(String city) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<Artist> findByMusicalStyle(Integer styleId) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<Artist> findByShowCity(String showCity) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<Artist> findByShowVenue(String showVenue) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<Artist> findByShowDate(LocalDate date) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Artist findByUserId(Integer userId) {
        try {
            connection = dataSource.getConnection();
            stmt = connection.prepareStatement(findByUserId);
            stmt.setInt(1, userId);
            ResultSet result = stmt.executeQuery();
            if (result.next()) {
                return instanciateObject(result);
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            DataSourceUtils.releaseConnection(connection, dataSource);
        }

        return null;
    }

    @Override
    public List<Artist> findByFavorites(Integer userId) {
        // TODO Auto-generated method stub
        return null;
    }


    @Override
    public boolean deleteById(Integer id) {
        socialNetworkRepository.deleteByArtistId(id);
        trackRepository.deleteByArtistId(id);
        return super.deleteById(id);
    }

    public SocialNetworkRepository getSocialNetworkRepository() {
        return socialNetworkRepository;
    }

    public void setSocialNetworkRepository(SocialNetworkRepository socialNetworkRepository) {
        this.socialNetworkRepository = socialNetworkRepository;
    }

    public TrackRepository getTrackRepository() {
        return trackRepository;
    }

    public void setTrackRepository(TrackRepository trackRepository) {
        this.trackRepository = trackRepository;
    }

    @Override
    public boolean saveShow(Artist artist) {
        // TODO Auto-generated method stub
        return false;
    }

  

}
