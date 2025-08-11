package com.furkanbilgin.finalproject.movieportal.service.impl;

import com.furkanbilgin.finalproject.movieportal.service.JWTService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import java.util.Date;
import java.util.function.Function;
import javax.crypto.SecretKey;
import org.springframework.stereotype.Service;

@Service
public class JWTServiceImpl implements JWTService {
  private final SecretKey secretKey;

  public JWTServiceImpl(SecretKey secretKey) {
    this.secretKey = secretKey;
  }

  public String generate(String username, Long ttlInMs) {
    return Jwts.builder()
        .subject(username)
        .issuedAt(new Date(System.currentTimeMillis()))
        .expiration(new Date(System.currentTimeMillis() + ttlInMs))
        .signWith(secretKey)
        .compact();
  }

  public String getUsername(String token) throws JwtException {
    return getClaim(token, Claims::getSubject);
  }

  public Date getExpirationDate(String token) throws JwtException {
    return getClaim(token, Claims::getExpiration);
  }

  public Date getCreatedAt(String token) throws JwtException {
    return getClaim(token, Claims::getIssuedAt);
  }

  public boolean isTokenValid(String token, String username) {
    return !isTokenExpired(token) && getUsername(token).equals(username);
  }

  private boolean isTokenExpired(String token) throws JwtException {
    return getExpirationDate(token).before(new Date());
  }

  private <T> T getClaim(String token, Function<Claims, T> claimsResolvers) throws JwtException {
    final Claims claims = getClaims(token);
    return claimsResolvers.apply(claims);
  }

  private Claims getClaims(String token) throws JwtException {
    return Jwts.parser().verifyWith(secretKey).build().parseSignedClaims(token).getPayload();
  }
}
