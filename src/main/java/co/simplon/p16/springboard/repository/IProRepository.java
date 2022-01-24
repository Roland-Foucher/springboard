package co.simplon.p16.springboard.repository;

import co.simplon.p16.springboard.entity.Pro;

public interface IProRepository extends IGlobalRepository<Pro> {
    Pro findByUser(Integer userId);

}
