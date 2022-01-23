package co.simplon.p16.springboard.repository;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.List;

/**
 * Global repository have methodes that need for every repositry to make a CRUD : 
 * findAll, findById, save, update, deleteById.
 * Every child must overide methodes to make object(find), and implements query(update and save)
 */
public interface IGlobalRepository <T> {


    List<T> findAll();
    T findById(Integer id);
    boolean save(T object);
    boolean update (T object);
    boolean deleteById (Integer id);

}
