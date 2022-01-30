package co.simplon.p16.springboard.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import javax.sql.DataSource;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

public class MusicalStyleRepositoryTest {

    MusicalStyleRepository musicalStyleRepository;

    @BeforeEach
    void init() {
        musicalStyleRepository = new MusicalStyleRepository();
        DataSource dataSource = new EmbeddedDatabaseBuilder()
                .setType(EmbeddedDatabaseType.H2)
                .addScript("schema.sql")
                .addScript("data.sql")
                .build();
        musicalStyleRepository.setDataSource(dataSource);

    }

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
