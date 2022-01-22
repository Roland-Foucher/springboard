package co.simplon.p16.springboard.repository;

import java.sql.ResultSet;

import co.simplon.p16.springboard.entity.User;

public interface IUserRepository extends IGlobalRepository<User> {
    User findByEmail(String email);

}
