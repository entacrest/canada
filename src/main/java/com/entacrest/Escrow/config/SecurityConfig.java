package com.entacrest.Escrow.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/api/v1/user/register").permitAll()  // Allow public access to registration
                        .anyRequest().authenticated()  // Protect other endpoints
                )
                .httpBasic(httpBasic -> {})  // Enable basic auth
                .csrf(csrf -> csrf.disable());  // Disable CSRF using the new lambda style

        return http.build();
    }
}
