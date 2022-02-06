package co.simplon.p16.springboard.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import co.simplon.p16.springboard.repository.UserRepository;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserRepository userRepository;

    /**
     * security configuration application.
     * select the url that should have authorization and role to access.
     * select the page to login and logout(auto configurate if same controller)
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .mvcMatchers("/user/**").hasRole("USER")
                .mvcMatchers("/artist/**").hasRole("ARTIST")
                .mvcMatchers("/account/**").authenticated()
                .anyRequest().permitAll()
                .and().formLogin()
                    .loginPage("/login")
                    .defaultSuccessUrl("/myAccount");
            
    }

    /**
     * configure spring boot security, select repository that contain user entity
     * and extends userDetailService.
     * Select the methode that encrypte password.
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userRepository).passwordEncoder(passwordEncoder());
    }

    /**
     * methode to encrypte with BCrypt the password.
     * 
     * @return password encrypted with 10 pass.
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(10);
    }
}
