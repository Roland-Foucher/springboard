package co.simplon.p16.springboard.repository;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;

import co.simplon.p16.springboard.entity.User;

@Repository
public class UserRepository extends GlobalRepository<User> implements IUserRepository, UserDetailsService {

    // Define specifics query
    private final String findByEmailQuery = "SELECT * FROM users WHERE email=?";
    private final String setUpVoteQuery = "INSERT INTO upVotes VALUES(?,?)";
    private final String deleteAllUsersUpvotesQuery = "DELETE FROM upVotes WHERE userId=?";
    private final String deleteSingleUsersUpvotesQuery = "DELETE FROM upVotes WHERE userId=? AND artistId=?";
    private final String setFavoritQuery = "INSERT INTO favoritsArtists VALUES(?,?)";
    private final String deleteSingleFavoritQuery = "DELETE FROM favoritsArtists WHERE userId=? AND artistId=?";
    private final String deleteAllUserFavoritQuery = "DELETE FROM favoritsArtists WHERE userId=?";


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
    protected void injectParamatersToSaveStatement(User user) throws SQLException {
            stmt.setString(1, user.getFirstName());
            stmt.setString(2, user.getLastName());
            stmt.setString(3, user.getEmail());
            stmt.setString(4, user.getPassword());
            stmt.setString(5, user.getRole());
    }

    @Override
    protected void injectParamatersToUpdateStatement(User user) throws SQLException {
        injectParamatersToSaveStatement(user);
            stmt.setInt(6, user.getId());
    }

    @Override
    protected User instanciateObject(ResultSet result) throws SQLException {
            return new User(result.getInt("id"),
                    result.getString("firstName"),
                    result.getString("lastName"),
                    result.getString("email"),
                    result.getString("password"),
                    result.getString("role"));
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
    public boolean setFavoriteArtist(Integer userId, Integer artistId) {

        return super.saveOrDeleteOnManyToManyTable(userId, artistId, setFavoritQuery);
    }

    @Override
    public Integer deleteAllUserUpvote(Integer userId) {

        return super.deleteByInteger(userId, deleteAllUsersUpvotesQuery);
    }

    @Override
    public boolean deleteSingleFavoriteArtist(Integer userId, Integer artistId) {

        return super.saveOrDeleteOnManyToManyTable(userId, artistId, deleteSingleFavoritQuery);
    }

    @Override
    public Integer deleteAllFavoriteArtist(Integer userId) {

        return super.deleteByInteger(userId, deleteAllUserFavoritQuery);
    }

    @Override
    public boolean deleteSingleUpvote(Integer userId, Integer artistId) {

        return super.saveOrDeleteOnManyToManyTable(userId, artistId, deleteSingleUsersUpvotesQuery );
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



}
