package com.furkanbilgin.finalproject.movieportal.security;

import com.furkanbilgin.finalproject.movieportal.config.PasswordHasher;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.util.Pair;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import java.util.List;

@Configuration
@EnableMethodSecurity
@EnableWebSecurity
public class SecurityConfig {
    private final List<Pair<HttpMethod, String>> allowedPaths =
            List.of(
                    // Allow Spring docs
                    Pair.of(HttpMethod.GET, "/swagger-ui/**"),
                    Pair.of(HttpMethod.GET, "/v3/api-docs/**"),
                    // Allow auth
                    Pair.of(HttpMethod.POST, "/auth/login/*"),
                    Pair.of(HttpMethod.POST, "/auth/register/"));

    @Bean
    public SecurityFilterChain springFilterChain(HttpSecurity http) throws Exception {
        return http.csrf(AbstractHttpConfigurer::disable)
                .cors(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(
                        auth ->
                                allowedPaths.stream()
                                        .reduce(
                                                auth,
                                                (currentAuth, pair) ->
                                                        currentAuth
                                                                .requestMatchers(
                                                                        pair.getFirst(),
                                                                        pair.getSecond())
                                                                .permitAll(),
                                                (a, b) -> a)
                                        .anyRequest()
                                        .authenticated())
                .build();
    }

    @Bean
    public PasswordEncoder passwordEncoder(PasswordHasher passwordHasher) {
        return passwordHasher.getPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(
            HttpSecurity http,
            CustomUserDetailsService customUserDetailsService,
            PasswordHasher passwordHasher)
            throws Exception {
        var builder = http.getSharedObject(AuthenticationManagerBuilder.class);
        builder.userDetailsService(customUserDetailsService)
                .passwordEncoder(passwordHasher.getPasswordEncoder());
        return builder.build();
    }
}
