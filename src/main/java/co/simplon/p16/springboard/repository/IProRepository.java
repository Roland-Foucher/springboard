package co.simplon.p16.springboard.repository;

import java.sql.ResultSet;

import co.simplon.p16.springboard.entity.Pro;
import co.simplon.p16.springboard.entity.User;

public interface IProRepository extends IGlobalRepository<Pro> {
    Pro findByUser(Integer userId);

}
