package com.example.dio.security.filter;

import com.example.dio.security.jwt.ClaimName;
import com.example.dio.security.jwt.JWTService;
import com.example.dio.security.jwt.TokenBlackListService;
import com.example.dio.security.jwt.TokenType;
import io.jsonwebtoken.Claims;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;

@Slf4j
@AllArgsConstructor
public class AuthFilter extends OncePerRequestFilter {

    private final JWTService jwtService;
    private final TokenBlackListService tokenBlackListService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        log.info("validating request , finding token  : {}", TokenType.ACCESS.type());
        Cookie[] cookies =request.getCookies();
        String token = FilterHelper.extractToken(TokenType.ACCESS,cookies);
        if(token != null){
            if (!tokenBlackListService.isBlackListed(token)) {
                log.info("token found : {}", TokenType.ACCESS.type());
                Claims claims = jwtService.parseToken(token);

                String email = claims.get(ClaimName.USER_EMAIL, String.class);
                String role = claims.get(ClaimName.USER_role, String.class);

                if ((email != null && !email.isBlank()) && (role != null && !role.isBlank())) {
                    if (SecurityContextHolder.getContext().getAuthentication() == null) {
                        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                                email,
                                null,
                                List.of(new SimpleGrantedAuthority(role)));
                        authToken.setDetails(request);
                        SecurityContextHolder.getContext().setAuthentication(authToken);
                    }
                }
            }
        }
        else{
            log.warn("Token not found with name : {}",TokenType.ACCESS.type());
        }
        filterChain.doFilter(request,response);
    }
}
