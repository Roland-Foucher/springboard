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
        this.saveQuery = "INSERT INTO socialNetwork (url,name,artistId) VALUES(?,?,?)";
        this.updateQuery = "UPDATE socialNetwork SET url=?, name=?, artistId=? WHERE id=?";
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
    protected void injectParamatersToSaveStatement(SocialNetwork socialNetwork) throws SQLException {
        stmt.setString(1, socialNetwork.getUrl());
        stmt.setString(2, socialNetwork.getName());
        stmt.setInt(3, socialNetwork.getArtistId());
    }

    @Override
    protected void injectParamatersToUpdateStatement(SocialNetwork socialNetwork) throws SQLException {
        injectParamatersToSaveStatement(socialNetwork);
        stmt.setInt(4, socialNetwork.getId());
    }

    @Override
    protected SocialNetwork instanciateObject(ResultSet result) throws SQLException {

        return new SocialNetwork(result.getInt("id"),
                result.getString("url"),
                result.getString("name"),
                result.getInt("artistId"));
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
