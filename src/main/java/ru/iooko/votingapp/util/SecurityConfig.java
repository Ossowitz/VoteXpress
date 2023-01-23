package ru.iooko.votingapp.util;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/h2-console/**").permitAll()
                .anyRequest().permitAll(); // Применять ограничения доступа для остальных URL

        http.csrf().disable(); // Отключить CSRF для консоли H2
        http.headers().frameOptions().disable(); // Разрешить отображение фрейма H2 в X-Frame-Options

        // Дополнительные настройки безопасности, если необходимо
    }
}