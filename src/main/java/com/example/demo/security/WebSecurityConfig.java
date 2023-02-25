package com.example.demo.security;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@RequiredArgsConstructor
@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

    public static final String USER = "fitnessuser";
    public static final String ADMIN = "fitnessadmin";

    private final JwtAuthConverter jwtAuthConverter;



    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests()
                .requestMatchers(HttpMethod.GET, "api/v1/workout/").hasAnyRole(ADMIN, USER)
                .requestMatchers(HttpMethod.POST, "api/v1/workout/").hasAnyRole(ADMIN, USER)
                .requestMatchers(HttpMethod.DELETE, "api/v1/workout/").hasAnyRole(ADMIN)
                .anyRequest().denyAll();
        http.oauth2ResourceServer()
                .jwt()
                .jwtAuthenticationConverter(jwtAuthConverter);
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        return http.build();
    }

}