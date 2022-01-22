package co.simplon.p16.springboard.repository;

import java.sql.ResultSet;
import java.util.List;

import co.simplon.p16.springboard.entity.Show;

public class ShowRepository extends GlobalRepository<Show> implements IShowRepository {

    @Override
    public List<Show> findByArtist(Integer artistId) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    protected void injectGeneratedKey(Show object, int generatedId) {
        // TODO Auto-generated method stub
        super.injectGeneratedKey(object, generatedId);
    }

    @Override
    protected void injectParamatersToSaveStatement(Show object) {
        // TODO Auto-generated method stub
        super.injectParamatersToSaveStatement(object);
    }

    @Override
    protected void injectParamatersToUpdateStatement(Show object) {
        // TODO Auto-generated method stub
        super.injectParamatersToUpdateStatement(object);
    }

    @Override
    protected Show instanciateObject(ResultSet result) {
        // TODO Auto-generated method stub
        return super.instanciateObject(result);
    }
    
}
