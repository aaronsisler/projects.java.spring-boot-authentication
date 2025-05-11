package com.ebsolutions.projects.java.springboot.authentication.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class WebSecurityConfig {
  private final CorsConfig corsConfig;

  @Bean
  public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
    return httpSecurity
        // Locking down which sites and IPs can call this service
        .cors(c -> c.configurationSource(corsConfig))
        .csrf(AbstractHttpConfigurer::disable)
        // Used to specify that Spring Security does not create or access a session
        .sessionManagement(
            session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
        // Set permissions on endpoints
        .authorizeHttpRequests(auth -> auth
            // Public endpoints
            .requestMatchers(HttpMethod.GET, "/actuator/health").permitAll()
            .requestMatchers(HttpMethod.GET, "/actuator/info").permitAll()
            // Private endpoints
            .anyRequest().authenticated())
        .build();
  }
}
