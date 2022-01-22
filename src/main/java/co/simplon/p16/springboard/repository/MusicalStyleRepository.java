package co.simplon.p16.springboard.repository;

import java.sql.ResultSet;

import co.simplon.p16.springboard.entity.MusicalStyle;

public class MusicalStyleRepository extends GlobalRepository<MusicalStyle> implements IMusicalStyleRepository  {

    @Override
    protected void injectGeneratedKey(MusicalStyle object, int generatedId) {
        // TODO Auto-generated method stub
        super.injectGeneratedKey(object, generatedId);
    }

    @Override
    protected void injectParamatersToSaveStatement(MusicalStyle object) {
        // TODO Auto-generated method stub
        super.injectParamatersToSaveStatement(object);
    }

    @Override
    protected void injectParamatersToUpdateStatement(MusicalStyle object) {
        // TODO Auto-generated method stub
        super.injectParamatersToUpdateStatement(object);
    }

    @Override
    protected MusicalStyle instanciateObject(ResultSet result) {
        // TODO Auto-generated method stub
        return super.instanciateObject(result);
    }
    
}
