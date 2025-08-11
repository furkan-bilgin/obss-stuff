package com.furkanbilgin.finalproject.movieportal.security;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component
@ConfigurationProperties(prefix = "application.security")
public class SecurityConfigProperties {
  private String secretKey;
  private Long jwtTtl;
}
