package co.simplon.p16.springboard.repository;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.stereotype.Repository;

import co.simplon.p16.springboard.entity.MusicalStyle;

@Repository
public class MusicalStyleRepository extends GlobalRepository<MusicalStyle> implements IMusicalStyleRepository {

    // define query from global repository
    public MusicalStyleRepository() {
        this.findAllQuery = "SELECT * FROM musicalStyle";
        this.findByIdQuery = "SELECT * FROM musicalStyle WHERE id=?";
    }

    //
    // Override GlobalRepository methodes
    //

    @Override
    protected MusicalStyle instanciateObject(ResultSet result) throws SQLException {

        return new MusicalStyle(result.getInt("id"), result.getString("styleName"));
    }

}
