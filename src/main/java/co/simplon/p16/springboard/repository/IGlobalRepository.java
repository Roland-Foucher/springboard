package co.simplon.p16.springboard.repository;

import java.util.List;

public interface IGlobalRepository<T> {

    /**
     * Methode findAll is global methode to find all element in a table
     * tell findAllWithParamQuery with a find all query type "SELECT * FROM x"
     * (defined in child constructor)
     */
    List<T> findAll();

    /**
     * Methode findVyId is global methode to find an element in a table by his id
     * tell findOneByForeignId with a find query type "SELECT * FROM x WHERE id=?"
     * (defined in child constructor)
     */
    T findById(Integer id);

    /**
     * Methode save is global methode to save an element to a table.
     * Child need to define saveQuery in constructor,
     * defined how to inject parameters to query and
     * defined how to inject generated key to object (object.setId())
     */
    boolean save(T object);

    /**
     * Methode update is global methode to save an element to a table.
     * Child need to define udateQuery in constructor and
     * defined how to inject parameters to query
     */
    boolean update(T object);

    /**
     * Methode delete is global methode to delete an element to a table.
     * Child need to define deleteQuery in constructor and
     * defined how to inject parameters to query
     */
    boolean deleteById(Integer id);

}
