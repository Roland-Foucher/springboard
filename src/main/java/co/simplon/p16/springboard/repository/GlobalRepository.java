package co.simplon.p16.springboard.repository;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceUtils;

public class GlobalRepository<T> implements IGlobalRepository<T> {

    // Connections attributs
    protected Connection connection;
    protected PreparedStatement stmt;

    @Autowired
    protected DataSource dataSource;

    // SQL query attribut
    protected String findAllQuery;
    protected String findByIdQuery;
    protected String saveQuery;
    protected String updateQuery;
    protected String deleteQuery;

    //
    // Global methods find, findAll, update, delete, save
    //

    @Override
    public List<T> findAll() {

        return this.findAllWithParamQuery(findAllQuery);
    }

    @Override
    public T findById(Integer id) {

        return this.findOneByForeignId(id, findByIdQuery);
    }

    @Override
    public boolean save(T object) {
        try {
            connection = dataSource.getConnection();
            stmt = connection.prepareStatement(saveQuery, PreparedStatement.RETURN_GENERATED_KEYS);
            injectParamatersToSaveStatement(object);

            if (stmt.executeUpdate() == 1) {
                ResultSet result = stmt.getGeneratedKeys();
                result.next();
                int generatedId = result.getInt(1);
                injectGeneratedKey(object, generatedId);

                return true;
            }

        } catch (SQLException e) {

            e.printStackTrace();
        } finally {

            DataSourceUtils.releaseConnection(connection, dataSource);
        }

        return false;
    }

    @Override
    public boolean update(T object) {
        try {
            connection = dataSource.getConnection();
            stmt = connection.prepareStatement(updateQuery);
            injectParamatersToUpdateStatement(object);

            return stmt.executeUpdate() == 1;

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {

            DataSourceUtils.releaseConnection(connection, dataSource);
        }
        return false;
    }

    @Override
    public boolean deleteById(Integer id) {
        try {
            connection = dataSource.getConnection();
            stmt = connection.prepareStatement(deleteQuery);
            stmt.setInt(1, id);

            return stmt.executeUpdate() == 1;
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            DataSourceUtils.releaseConnection(connection, dataSource);
        }

        return false;
    }

    //
    // Global methodes whith other query, other parametres,...
    //

    protected T findByString(String element, String query) {
        try {
            connection = dataSource.getConnection();
            stmt = connection.prepareStatement(query);
            stmt.setString(1, element);
            ResultSet result = stmt.executeQuery();
            if (result.next()) {
                return instanciateObject(result);
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            DataSourceUtils.releaseConnection(connection, dataSource);
        }
        return null;
    }

    protected List<T> findListByString(String element, String query) {
        List<T> list = new ArrayList<>();
        try {
            connection = dataSource.getConnection();
            stmt = connection.prepareStatement(query);
            stmt.setString(1, element);
            ResultSet result = stmt.executeQuery();
            while (result.next()) {
                list.add(instanciateObject(result));
            }
            return list;

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            DataSourceUtils.releaseConnection(connection, dataSource);
        }

        return null;
    }
    protected List<T> findListByDate(LocalDate date, String query) {
        List<T> list = new ArrayList<>();
        try {
            connection = dataSource.getConnection();
            stmt = connection.prepareStatement(query);
            stmt.setDate(1, Date.valueOf(date));
            ResultSet result = stmt.executeQuery();
            while (result.next()) {
                list.add(instanciateObject(result));
            }
            return list;

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            DataSourceUtils.releaseConnection(connection, dataSource);
        }

        return null;
    }

    protected T findOneByForeignId(Integer id, String query) {
        try {
            connection = dataSource.getConnection();
            stmt = connection.prepareStatement(query);
            stmt.setInt(1, id);
            ResultSet result = stmt.executeQuery();
            if (result.next()) {
                return instanciateObject(result);
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            DataSourceUtils.releaseConnection(connection, dataSource);
        }

        return null;
    }

    protected List<T> findListByforeignId(Integer id, String query) {
        List<T> list = new ArrayList<>();
        try {
            connection = dataSource.getConnection();
            stmt = connection.prepareStatement(query);
            stmt.setInt(1, id);
            ResultSet result = stmt.executeQuery();
            while (result.next()) {
                list.add(instanciateObject(result));
            }
            return list;

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            DataSourceUtils.releaseConnection(connection, dataSource);
        }

        return null;
    }
    protected List<T> findAllWithParamQuery(String query){
        List<T> list = new ArrayList<>();
        try {
            connection = dataSource.getConnection();
            stmt = connection.prepareStatement(query);
            ResultSet result = stmt.executeQuery();
            while (result.next()) {
                list.add(instanciateObject(result));
            }
            return list;

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            DataSourceUtils.releaseConnection(connection, dataSource);
        }

        return null;
    }
    
    
    protected Integer deleteByForeignId(Integer id, String query) {
        try {
            connection = dataSource.getConnection();
            stmt = connection.prepareStatement(query);
            stmt.setInt(1, id);

            return stmt.executeUpdate();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            DataSourceUtils.releaseConnection(connection, dataSource);
        }

        return null;
    }

    /**
     * Save or delete line on a many to many table
     * @param id1 id left to delete
     * @param id2 id right to delete
     * @param query string with SQL query
     * @return boolean if delete ok or not
     */
    protected boolean saveOrDeleteOnManyToManyTable(Integer id1, Integer id2, String query) {
        try {
            connection = dataSource.getConnection();
            stmt = connection.prepareStatement(query);
            stmt.setInt(1, id1);
            stmt.setInt(2, id2);

            return stmt.executeUpdate() == 1;
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            DataSourceUtils.releaseConnection(connection, dataSource);
        }

        return false;

    }

    //
    // Methodes to overide by children
    //

    /**
     * Methode to define how make object with the result of the query
     * 
     * @param result result of the query when find on database
     * @return Object instanciated
     */
    protected T instanciateObject(ResultSet result) {

        return null;
    }

    /**
     * define how to inject parameters to SQL query when save in database
     * 
     * @param object object to save on database
     */
    protected void injectParamatersToSaveStatement(T object) {

    }

    /**
     * define how to inject parameters to SQL query when update in database
     * 
     * @param object to save on database
     */
    protected void injectParamatersToUpdateStatement(T object) {
    }

    /**
     * called setId on object saved in database to inject id generated when add
     * object.
     * 
     * @param object      object saved on database
     * @param generatedId id generated by database
     */
    protected void injectGeneratedKey(T object, int generatedId) {

    }

    /**
     * set Datasource H2 to run test units
     * 
     * @param dataSource datasource made for test inject with H2 driver
     */
    protected void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public DataSource getDataSource() {
        return dataSource;
    }

}
