package com.furkanbilgin.finalproject.movieportal.security;

import com.furkanbilgin.finalproject.movieportal.config.PasswordHasher;
import com.furkanbilgin.finalproject.movieportal.security.filter.JWTAuthenticationFilter;
import io.jsonwebtoken.security.Keys;
import javax.crypto.SecretKey;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableMethodSecurity
@EnableWebSecurity
public class SecurityConfig {
  @Bean
  public SecurityFilterChain springFilterChain(
      HttpSecurity http, JWTAuthenticationFilter jwtAuthenticationFilter) throws Exception {
    return http.csrf(AbstractHttpConfigurer::disable)
        .cors(AbstractHttpConfigurer::disable)
        .authorizeHttpRequests(
            auth ->
                auth.requestMatchers(
                        "/swagger-ui/**", "/v3/api-docs/**", "/auth/login", "/auth/register")
                    .permitAll()
                    .anyRequest()
                    .authenticated())
        .sessionManagement(
            session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
        .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
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
    builder
        .userDetailsService(customUserDetailsService)
        .passwordEncoder(passwordHasher.getPasswordEncoder());
    return builder.build();
  }

  @Bean
  public SecretKey jwtSecretKey(SecurityConfigProperties securityConfigProperties) {
    return Keys.hmacShaKeyFor(securityConfigProperties.getSecretKey().getBytes());
  }
}
