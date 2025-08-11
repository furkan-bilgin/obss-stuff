package com.furkanbilgin.finalproject.movieportal.service;

import io.jsonwebtoken.JwtException;
import java.util.Date;

public interface JWTService {
  String generate(String username, Long ttlInMs);

  String getUsername(String token) throws JwtException;

  Date getExpirationDate(String token) throws JwtException;

  Date getCreatedAt(String token) throws JwtException;

  boolean isTokenValid(String token, String username);
}
