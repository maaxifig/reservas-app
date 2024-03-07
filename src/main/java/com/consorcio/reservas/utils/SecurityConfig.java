package com.consorcio.reservas.utils;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                // Permitir el acceso público a ciertos endpoints
                .antMatchers("/api/users/**").permitAll()
                // Restringir el acceso a otros endpoints
                .anyRequest().authenticated()
                .and()
                .csrf().disable(); // Deshabilitar CSRF (si es necesario)
    }
}

