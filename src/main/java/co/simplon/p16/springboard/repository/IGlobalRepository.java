package co.simplon.p16.springboard.repository;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.List;

public interface IGlobalRepository <T> {
    List<T> findAll();
    T findById(Integer id);
    boolean save(T object);
    boolean update (T object);
    boolean deleteById (Integer id);
    T instanciateObject(ResultSet result);
    void injectParamatersToAddStatement(T object);
    void injectParamatersToUpdateStatement(T object);
    void injectGeneratedKey(T object, int generatedId);
    Connection getConnection();
    void setConnection();
}
