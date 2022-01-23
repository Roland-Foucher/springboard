package co.simplon.p16.springboard.repository;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.stereotype.Repository;

import co.simplon.p16.springboard.entity.Show;

@Repository
public class ShowRepository extends GlobalRepository<Show> implements IShowRepository {

    public ShowRepository() {
        this.findAllQuery = "SELECT * FROM shows";
        this.findByIdQuery = "SELECT * FROM shows WHERE id=?";
        this.saveQuery = "INSERT INTO shows (date,venue,adress) VALUES(?,?,?)";
        this.updateQuery = "UPDATE shows SET date=?, venue=?, adress=? WHERE id=?";
        this.deleteQuery = "DELETE FROM shows WHERE id=?";
    }

    @Override
    protected void injectGeneratedKey(Show show, int generatedId) {
        show.setId(generatedId);
    }

    @Override
    protected void injectParamatersToSaveStatement(Show show) {
        try {
            stmt.setDate(1, Date.valueOf(show.getDate()));
            stmt.setString(2, show.getAdress());
            stmt.setString(3, show.getVenue());
        } catch (SQLException e) {
            System.out.println("error when inject parameters in query on save show");
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @Override
    protected void injectParamatersToUpdateStatement(Show show) {
        injectParamatersToSaveStatement(show);
        try {
            stmt.setInt(4, show.getId());
        } catch (SQLException e) {
            System.out.println("error when inject parameters in query on update show");
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @Override
    protected Show instanciateObject(ResultSet result) {
        try {
            return new Show(result.getInt("id"),
                    result.getDate("date").toLocalDate(),
                    result.getString("venue"),
                    result.getString("adress"));
        } catch (SQLException e) {
            System.out.println("error on instanciate show object wen find query");
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Show> findByArtist(Integer artistId) {
        // TODO Auto-generated method stub
        return null;
    }


}
