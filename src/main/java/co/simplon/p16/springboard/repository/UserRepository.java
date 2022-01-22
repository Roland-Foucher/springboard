package co.simplon.p16.springboard.repository;

import java.sql.ResultSet;

import co.simplon.p16.springboard.entity.User;

public class UserRepository extends GlobalRepository<User> implements IUserRepository {

    @Override
    public User findByEmail(String email) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    protected void injectGeneratedKey(User object, int generatedId) {
        // TODO Auto-generated method stub
        super.injectGeneratedKey(object, generatedId);
    }

    @Override
    protected void injectParamatersToSaveStatement(User object) {
        // TODO Auto-generated method stub
        super.injectParamatersToSaveStatement(object);
    }

    @Override
    protected void injectParamatersToUpdateStatement(User object) {
        // TODO Auto-generated method stub
        super.injectParamatersToUpdateStatement(object);
    }

    @Override
    protected User instanciateObject(ResultSet result) {
        // TODO Auto-generated method stub
        return super.instanciateObject(result);
    }
    
}
