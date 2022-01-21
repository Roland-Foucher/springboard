package co.simplon.p16.springboard.configuration.JdbcConfiguration;

import javax.sql.DataSource;

import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class JdbcConfiguration {
    
    @Bean
    public DataSource getDataSource()
    {
        DataSourceBuilder dataSourceBuilder = DataSourceBuilder.create();
        dataSourceBuilder.driverClassName("com.mysql.cj.jdbc.Driver");
        dataSourceBuilder.url("jdbc:mysql://localhost:3306/springboard");
        dataSourceBuilder.username("simplon");
        dataSourceBuilder.password("1234");
        return dataSourceBuilder.build();
    }
}
