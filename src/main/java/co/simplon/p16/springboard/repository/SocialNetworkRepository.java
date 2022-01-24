package co.simplon.p16.springboard.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.stereotype.Repository;

import co.simplon.p16.springboard.entity.SocialNetwork;

@Repository
public class SocialNetworkRepository extends GlobalRepository<SocialNetwork> implements ISocialNetworkRepository {

    // Define specifics query
    private final String deleteByArtistQuery = "DELETE FROM socialNetwork WHERE artistId=?";
    private final String findByArtistQuery = "SELECT * FROM socialNetwork WHERE artistId=?";

    // define query from global repository
    public SocialNetworkRepository() {
        this.findAllQuery = "SELECT * FROM socialNetwork";
        this.findByIdQuery = "SELECT * FROM socialNetwork WHERE id=?";
        this.saveQuery = "INSERT INTO socialNetwork (url,artistId) VALUES(?,?)";
        this.updateQuery = "UPDATE socialNetwork SET url=?, artistId=? WHERE id=?";
        this.deleteQuery = "DELETE FROM socialNetwork WHERE id=?";
    }

    //
    // Override GlobalRepository methodes
    //

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

    //
    // Add specifics methods
    //

    @Override
    public Integer deleteByArtistId(Integer artistId) {

        return super.deleteByInteger(artistId, deleteByArtistQuery);
    }

    @Override
    public List<SocialNetwork> findByArtistId(Integer artistId) {

        return super.findListByInteger(artistId, findByArtistQuery);
    }

}
