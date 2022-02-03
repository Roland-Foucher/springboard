package co.simplon.p16.springboard.repository;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.stereotype.Repository;

import co.simplon.p16.springboard.entity.Pro;

@Repository
public class ProRepository extends GlobalRepository<Pro> implements IProRepository {

    // Define specifics query
    private final String findByUserId = "SELECT * FROM pro WHERE userId=?";

    // define query from global repository
    public ProRepository() {
        this.findAllQuery = "SELECT * FROM pro";
        this.findByIdQuery = "SELECT * FROM pro WHERE id=?";
        this.saveQuery = "INSERT INTO pro (companyName, activity, contact, city, siret, userId) VALUES(?,?,?,?,?,?)";
        this.updateQuery = "UPDATE pro SET companyName=?, activity=?, contact=?, city=?, siret=?, userId=?  WHERE id=?";
        this.deleteQuery = "DELETE FROM pro WHERE id=?";
    }

    //
    // Override GlobalRepository methodes
    //

    @Override
    protected void injectGeneratedKey(Pro pro, int generatedId) {
        pro.setId(generatedId);
    }

    @Override
    protected void injectParamatersToSaveStatement(Pro pro) {
        try {
            stmt.setString(1, pro.getComagnyName());
            stmt.setString(2, pro.getActivity());
            stmt.setString(3, pro.getContact());
            stmt.setString(4, pro.getCity());
            stmt.setString(5, pro.getSiret());
            stmt.setInt(6, pro.getUserId());

        } catch (SQLException e) {
            System.out.println("error when inject parameters to save query");
            e.printStackTrace();
        }
    }

    @Override
    protected void injectParamatersToUpdateStatement(Pro pro) {
        injectParamatersToSaveStatement(pro);
        try {
            stmt.setInt(7, pro.getId());
        } catch (SQLException e) {
            System.out.println("error when inject parameters to update query");
            e.printStackTrace();
        }
    }

    @Override
    protected Pro instanciateObject(ResultSet result) {
        try {
            return new Pro(result.getInt("id"),
                    result.getString("companyName"),
                    result.getString("activity"),
                    result.getString("contact"),
                    result.getString("city"),
                    result.getString("siret"),
                    result.getInt("userId"));
        } catch (SQLException e) {
            System.out.println("error on instanciate pro object");
            e.printStackTrace();
        }
        return null;
    }

    //
    // Add specifics methods
    //

    @Override
    public Pro findByUser(Integer userId) {

        return this.findOneByInteger(userId, findByUserId);
    }
}
