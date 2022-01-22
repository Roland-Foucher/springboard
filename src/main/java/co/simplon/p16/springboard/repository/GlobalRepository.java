package co.simplon.p16.springboard.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceUtils;

import co.simplon.p16.springboard.configuration.JdbcConfiguration.JdbcConfiguration;


public class GlobalRepository<T> implements IGlobalRepository<T> {

    // Connections attributs
    protected Connection connection;
    protected PreparedStatement stmt;
    protected DataSource dataSource;
    protected JdbcConfiguration jdbcConfiguration;

    // SQL query attribut
    protected String findAllQuery;
    protected String findByIdQuery;
    protected String saveQuery;
    protected String updateQuery;
    protected String deleteQuery;

    public GlobalRepository() {
        jdbcConfiguration = new JdbcConfiguration();
        dataSource = jdbcConfiguration.getDataSource();
    }

    @Override
    public List<T> findAll() {

        List<T> list = new ArrayList<>();
        try {
            connection = dataSource.getConnection();
            stmt = connection.prepareStatement(findAllQuery);
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

    @Override
    public T findById(Integer id) {

        try {
            connection = dataSource.getConnection();
            stmt = connection.prepareStatement(findByIdQuery);
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
        }finally {

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

   
    protected T instanciateObject(ResultSet result) {

        return null;
    }


    
    protected void injectParamatersToSaveStatement(T object) {

    }

    
    protected void injectParamatersToUpdateStatement(T object) {
    }

   
    protected void injectGeneratedKey(T object, int generatedId) {

    }

    public DataSource getDataSource() {
        return dataSource;
    }

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

}
