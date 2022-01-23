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

    // Define specifics query
    private final String findByEmailQuery = "SELECT * FROM users WHERE email=?";
    private final String setUpVoteQuery = "INSERT INTO upVotes VALUES(?,?)";
    private final String deleteAllUsersUpvotesQuery = "DELETE FROM upVotes WHERE userId=?";
    private final String setFavoritQuery = "INSERT INTO favoritsArtists VALUES(?,?)";
    private final String deleteSingleFavoritQuery = "DELETE FROM favoritsArtists WHERE userId=? AND artistId=?";
    private final String deleteAllUserFavoritQuery = "DELETE FROM favoritsArtists WHERE userId=?";

    // get other repository for delete user
    @Autowired
    private ArtistRepository artistRepository;
    @Autowired
    private ProRepository proRepository;

    // define query from global repository
    public UserRepository() {
        this.findAllQuery = "SELECT * FROM users";
        this.findByIdQuery = "SELECT * FROM users WHERE id=?";
        this.saveQuery = "INSERT INTO users (firstName, lastName, email, password, role) VALUES(?,?,?,?,?)";
        this.updateQuery = "UPDATE users SET firstName=?, lastName=?, email=?, password=?, role=? WHERE id=?";
        this.deleteQuery = "DELETE FROM users WHERE id=?";
    }

    //
    // Override GlobalRepository methodes
    //

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
    public boolean deleteById(Integer id) {
        this.deleteAllFavoriteArtist(id);
        this.deleteAllUserUpvote(id);
        Artist artist = artistRepository.findByUserId(id);
        artistRepository.deleteById(artist.getId());
        Pro pro = proRepository.findByUser(id);
        proRepository.deleteById(pro.getId());
        return super.deleteById(id);

    }

    //
    // add specifics methods for User
    //

    @Override
    public User findByEmail(String email) {
        return super.findByString(email, findByEmailQuery);
    }

    @Override
    public boolean setUpvote(Integer artistId, Integer userId) {

       return super.saveOrDeleteOnManyToManyTable(artistId, userId, setUpVoteQuery);
    }

    @Override
    public boolean setFavoriteArtist(Integer artistId, Integer userId) {

        return super.saveOrDeleteOnManyToManyTable(artistId, userId, setFavoritQuery);
    }

    @Override
    public Integer deleteAllUserUpvote(Integer userId) {

        return super.deleteByForeignId(userId, deleteAllUsersUpvotesQuery);
    }

    @Override
    public boolean deleteSingleFavoriteArtist(Integer userId, Integer artistId) {

        return super.saveOrDeleteOnManyToManyTable(userId, artistId, deleteSingleFavoritQuery);
    }

    @Override
    public Integer deleteAllFavoriteArtist(Integer userId) {

        return super.deleteByForeignId(userId, deleteAllUserFavoritQuery);
    }

    //
    // Configure user security
    //

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = this.findByEmail(username);
        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }
        return user;
    }

    //
    // GETTER AND SETTER
    //

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

}
