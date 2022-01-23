package co.simplon.p16.springboard.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.stereotype.Repository;

import co.simplon.p16.springboard.entity.SocialNetwork;

@Repository
public class SocialNetworkRepository extends GlobalRepository<SocialNetwork> implements ISocialNetworkRepository {

    private final String deleteByArtistQuery = this.deleteQuery = "DELETE FROM socialNetwork WHERE artistId=?";

    public SocialNetworkRepository() {
        this.findAllQuery = "SELECT * FROM socialNetwork";
        this.findByIdQuery = "SELECT * FROM socialNetwork WHERE id=?";
        this.saveQuery = "INSERT INTO socialNetwork (url,artistId) VALUES(?,?)" ;
        this.updateQuery = "UPDATE socialNetwork SET url=?, artistId=? WHERE id=?";
        this.deleteQuery = "DELETE FROM socialNetwork WHERE id=?";
       }

    @Override
    protected void injectGeneratedKey(SocialNetwork socialNetwork, int generatedId) {
        socialNetwork.setId(generatedId);
    }

    @Override
    protected void injectParamatersToSaveStatement(SocialNetwork socialNetwork) {
        try {
            stmt.setString(1, socialNetwork.getUrl());
            stmt.setInt(2, socialNetwork.getArtistId());
        } catch (SQLException e) {
            System.out.println("error when inject parameters in query on save socialNetwork");
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @Override
    protected void injectParamatersToUpdateStatement(SocialNetwork socialNetwork) {
        injectParamatersToSaveStatement(socialNetwork);
        try {
            stmt.setInt(3, socialNetwork.getId());
        } catch (SQLException e) {
            System.out.println("error when inject parameters in query on update socialNetwork");
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @Override
    protected SocialNetwork instanciateObject(ResultSet result) {
        try {
            return new SocialNetwork(result.getInt("id"), 
                                    result.getString("url"), 
                                    result.getInt("artistId"));
        } catch (SQLException e) {
            System.out.println("error when instanciate socialNetwork object on find query");
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean deleteByArtistId(Integer artistId) {
        
        try {
            connection = dataSource.getConnection();
            stmt = connection.prepareStatement(deleteByArtistQuery);
            stmt.setInt(1, artistId);

            return stmt.executeUpdate() == 1;
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            DataSourceUtils.releaseConnection(connection, dataSource);
        }

        return false;
    }

    @Override
    public List<SocialNetwork> findByArtistId(Integer artistId) {
        // TODO Auto-generated method stub
        return null;
    }
    
}
