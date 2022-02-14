package co.simplon.p16.springboard.repositorySpringtest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;

import co.simplon.p16.springboard.repository.MusicalStyleRepository;

@SpringBootTest
@ActiveProfiles("test")

public class MusicalStyleRepositoryTest {

    @Autowired
    MusicalStyleRepository musicalStyleRepository;



    @Test
    void testFindById() {
        assertNotNull(musicalStyleRepository.findById(1));
        assertEquals(3, musicalStyleRepository.findById(3).getId());
    }


    @Test
    void testFindAll() {
        assertNotEquals(0,musicalStyleRepository.findAll());
    }

}
