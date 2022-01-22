package co.simplon.p16.springboard.repository;

import java.sql.ResultSet;

import co.simplon.p16.springboard.entity.Pro;

public class ProRepository extends GlobalRepository<Pro> implements IProRepository {

    @Override
    public Pro findByUser(Integer userId) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    protected void injectGeneratedKey(Pro object, int generatedId) {
        // TODO Auto-generated method stub
        super.injectGeneratedKey(object, generatedId);
    }

    @Override
    protected void injectParamatersToSaveStatement(Pro object) {
        // TODO Auto-generated method stub
        super.injectParamatersToSaveStatement(object);
    }

    @Override
    protected void injectParamatersToUpdateStatement(Pro object) {
        // TODO Auto-generated method stub
        super.injectParamatersToUpdateStatement(object);
    }

    @Override
    protected Pro instanciateObject(ResultSet result) {
        // TODO Auto-generated method stub
        return super.instanciateObject(result);
    }
    
}
