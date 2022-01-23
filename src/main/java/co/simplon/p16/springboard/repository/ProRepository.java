package co.simplon.p16.springboard.repository;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.stereotype.Repository;

import co.simplon.p16.springboard.entity.Pro;

@Repository
public class ProRepository extends GlobalRepository<Pro> implements IProRepository {

    private final String findByUserId =  "SELECT * FROM pro WHERE userId=?";

    public ProRepository() {
        this.findAllQuery = "SELECT * FROM pro";
        this.findByIdQuery = "SELECT * FROM pro WHERE id=?";
        this.saveQuery = "INSERT INTO pro (companyName, activity, contact, city, siret, userId) VALUES(?,?,?,?,?,?)" ;
        this.updateQuery = "UPDATE pro SET companyName=?, activity=?, contact=?, city=?, siret=?, userId=?  WHERE id=?";
        this.deleteQuery = "DELETE FROM pro WHERE id=?";
       }

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
            // TODO Auto-generated catch block
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
            // TODO Auto-generated catch block
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
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Pro findByUser(Integer userId) {
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


}
