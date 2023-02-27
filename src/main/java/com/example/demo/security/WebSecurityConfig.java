package com.example.demo.security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.RegexRequestMatcher;

@RequiredArgsConstructor
@Configuration
@EnableMethodSecurity(
        prePostEnabled = true,  // Enables @PreAuthorize and @PostAuthorize
        securedEnabled = true, // Enables @Secured
        jsr250Enabled = true    // Enables @RolesAllowed (Ensures JSR-250 annotations are enabled)
)
public class WebSecurityConfig {

    public static final String USER = "fitnessuser";
    public static final String ADMIN = "fitnessadmin";

    private final JwtAuthConverter jwtAuthConverter;



    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests()
                .requestMatchers(HttpMethod.GET, "/api/v1/workout").hasAnyRole(ADMIN, USER)
                .requestMatchers(HttpMethod.POST, "/api/v1/workout").hasAnyRole(ADMIN, USER)
                .requestMatchers(new RegexRequestMatcher("\\/api\\/v1\\/workout\\/[0-9]{1,4}", HttpMethod.DELETE.name())).hasRole(ADMIN)
                .anyRequest().denyAll();
        http.oauth2ResourceServer()
                .jwt()
                .jwtAuthenticationConverter(jwtAuthConverter);
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        return http.build();
    }

}