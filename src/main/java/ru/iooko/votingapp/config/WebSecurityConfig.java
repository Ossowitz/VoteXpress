package ru.iooko.votingapp.config;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import ru.iooko.votingapp.model.Users;
import ru.iooko.votingapp.repository.UserRepository;
import ru.iooko.votingapp.util.security.AuthUser;

import java.util.Optional;

@Slf4j
@Configuration
@EnableWebSecurity
@AllArgsConstructor
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private final UserRepository userRepository;

    /**
     * method defines how Spring will look up users in the database.
     *      In this case, `UserRepository` is used to search for a user by email.
     *          If the user is not found, a `UsernameNotFoundException` exception is thrown.
     */
    @Bean
    public UserDetailsService userDetailsService() {
        return email -> {
            log.debug("Authentication '{}'", email);
            Optional<Users> user = userRepository.getByEmail(email);
            return new AuthUser(user.orElseThrow(
                    () -> new UsernameNotFoundException("User '" + email + "' was not found"))
            );
        };
    }
}