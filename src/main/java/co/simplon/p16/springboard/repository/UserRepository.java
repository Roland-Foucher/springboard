package co.simplon.p16.springboard.repository;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;

import co.simplon.p16.springboard.entity.Artist;
import co.simplon.p16.springboard.entity.Pro;
import co.simplon.p16.springboard.entity.User;

@Repository
public class UserRepository extends GlobalRepository<User> implements IUserRepository, UserDetailsService {

    private final String findByEmailQuery = "SELECT * FROM users WHERE email=?";
   
    @Autowired
    private ArtistRepository artistRepository;

    @Autowired
    private ProRepository proRepository;

    public UserRepository() {
        this.findAllQuery = "SELECT * FROM users";
        this.findByIdQuery = "SELECT * FROM users WHERE id=?";
        this.saveQuery = "INSERT INTO users (firstName, lastName, email, password, role) VALUES(?,?,?,?,?)" ;
        this.updateQuery = "UPDATE users SET firstName=?, lastName=?, email=?, password=?, role=? WHERE id=?";
        this.deleteQuery = "DELETE FROM users WHERE id=?";
       }

    @Override
    protected void injectGeneratedKey(User user, int generatedId) {
        user.setId(generatedId);
    }

    @Override
    protected void injectParamatersToSaveStatement(User user) {
        try {
            stmt.setString(1, user.getFirstName());
            stmt.setString(2, user.getLastName());
            stmt.setString(3, user.getEmail());
            stmt.setString(4, user.getPassword());
            stmt.setString(5, user.getRole());
        } catch (SQLException e) {
            System.out.println("error on inject parameters on user to save statement");
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @Override
    protected void injectParamatersToUpdateStatement(User user) {
        injectParamatersToSaveStatement(user);
        try {
            stmt.setInt(6, user.getId());
        } catch (SQLException e) {
            System.out.println("error on inject parameters on user to update statement");
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @Override
    protected User instanciateObject(ResultSet result) {
        
        try {
            return new User(result.getInt("id"), 
                            result.getString("firstName"),
                            result.getString("lastName"),
                            result.getString("email"),
                            result.getString("password"),
                            result.getString("role"));
        } catch (SQLException e) {
            System.out.println("error when instanciate User object on find query");
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }
    @Override
    public User findByEmail(String email) {
        try {
            connection = dataSource.getConnection();
            stmt = connection.prepareStatement(findByEmailQuery);
            stmt.setString(1, email);
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
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = this.findByEmail(username);
        if(user == null){
            throw new UsernameNotFoundException("User not found");
        }
        return user;
    }

    @Override
    public boolean deleteById(Integer id) {
        Artist artist =  artistRepository.findByUserId(id);
        artistRepository.deleteById(artist.getId());
        Pro pro = proRepository.findById(id);
        proRepository.deleteById(pro.getId());
        return super.deleteById(id);
        
    }

    public ArtistRepository getArtistRepository() {
        return artistRepository;
    }

    public void setArtistRepository(ArtistRepository artistRepository) {
        this.artistRepository = artistRepository;
    }

    public ProRepository getProRepository() {
        return proRepository;
    }

    public void setProRepository(ProRepository proRepository) {
        this.proRepository = proRepository;
    }

    @Override
    public boolean setUpvote(Integer artistId) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean setFavoriteArtist(Integer artistId) {
        // TODO Auto-generated method stub
        return false;
    }
    
}
