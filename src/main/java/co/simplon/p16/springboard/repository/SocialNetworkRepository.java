package co.simplon.p16.springboard.repository;

import java.sql.ResultSet;

import co.simplon.p16.springboard.entity.SocialNetwork;

public class SocialNetworkRepository extends GlobalRepository<SocialNetwork> implements ISocialNetworkRepository {

    @Override
    protected void injectGeneratedKey(SocialNetwork object, int generatedId) {
        // TODO Auto-generated method stub
        super.injectGeneratedKey(object, generatedId);
    }

    @Override
    protected void injectParamatersToSaveStatement(SocialNetwork object) {
        // TODO Auto-generated method stub
        super.injectParamatersToSaveStatement(object);
    }

    @Override
    protected void injectParamatersToUpdateStatement(SocialNetwork object) {
        // TODO Auto-generated method stub
        super.injectParamatersToUpdateStatement(object);
    }

    @Override
    protected SocialNetwork instanciateObject(ResultSet result) {
        // TODO Auto-generated method stub
        return super.instanciateObject(result);
    }
    
}
