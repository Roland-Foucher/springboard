package co.simplon.p16.springboard.repository;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.stereotype.Repository;

import co.simplon.p16.springboard.entity.MusicalStyle;

@Repository
public class MusicalStyleRepository extends GlobalRepository<MusicalStyle> implements IMusicalStyleRepository  {

    public MusicalStyleRepository() {
        this.findAllQuery = "SELECT * FROM musicalStyle";
        this.findByIdQuery = "SELECT * FROM musicalStyle WHERE id=?";
       
    }

    @Override
    protected MusicalStyle instanciateObject(ResultSet result) {
        try {
            return new MusicalStyle(result.getInt("id"), result.getString("styleName"));
        } catch (SQLException e) {
            System.out.println("error when instanciate MusicalStyle");
            e.printStackTrace();
        }
        return null;
    }
    
}
