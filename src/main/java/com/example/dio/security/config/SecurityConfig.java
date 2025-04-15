package com.example.dio.security.config;

import com.example.dio.config.AppEnv;
import com.example.dio.security.filter.AuthFilter;
import com.example.dio.security.filter.RefreshAuthFilter;
import com.example.dio.security.jwt.JWTService;
import com.example.dio.security.jwt.TokenBlackListService;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@AllArgsConstructor
@EnableMethodSecurity
public class SecurityConfig {

    private final JWTService jwtService;
    private final AppEnv appEnv;
    private final TokenBlackListService tokenBlackListService;

    private String[] getPublicEndpoints() {
        String[] endpoints = new String[appEnv.getSecurity().getPublicEndpoints().size()];
        for (int i = 0; i < appEnv.getSecurity().getPublicEndpoints().size(); i++) {
            endpoints[i] = appEnv.getBaseUrl() + appEnv.getSecurity().getPublicEndpoints().get(i);
        }
        return endpoints;
    }

    @Bean
    AuthenticationManager authenticationManager
            (AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    @Order(2)
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        String base_url = appEnv.getBaseUrl();
        return http.csrf(csrf -> csrf.disable())
                .securityMatchers(match -> match.requestMatchers(
                        base_url + "/**"))
                .authorizeHttpRequests(authorize ->
                        authorize
                                .requestMatchers(getPublicEndpoints())
                                .permitAll()
                                .anyRequest().authenticated())
                .sessionManagement(session ->
                        session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .addFilterBefore(new AuthFilter(jwtService, tokenBlackListService), UsernamePasswordAuthenticationFilter.class)
                .build();
    }

    @Bean
    @Order(1)
    SecurityFilterChain refreshFilterChain(HttpSecurity http) throws Exception {
        String base_url = appEnv.getBaseUrl();
        return http.csrf(csrf -> csrf.disable())
                .securityMatchers(match -> match.requestMatchers(
                        base_url + "/refresh-login/**"))
                .authorizeHttpRequests(authorize ->
                        authorize.anyRequest().authenticated())
                .sessionManagement(session ->
                        session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .addFilterBefore(new RefreshAuthFilter(jwtService, tokenBlackListService)
                        , UsernamePasswordAuthenticationFilter.class)
                .build();
    }
}
//.requestMatchers(base_url + "/Restaurant/{userId}").hasAuthority("ADMIN")
