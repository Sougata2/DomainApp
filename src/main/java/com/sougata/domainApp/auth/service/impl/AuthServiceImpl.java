package com.sougata.domainApp.auth.service.impl;

import com.sougata.domainApp.auth.security.DomainUserDetailsService;
import com.sougata.domainApp.auth.service.AuthService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final AuthenticationManager authenticationManager;
    private final DomainUserDetailsService userDetailsService;

    @Value("${jwt.secret}")
    private String secretKey;
    private final long jwtExpiresMs = 86400000L;


    @Override
    public UserDetails authenticate(String email, String password) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(email, password)
            );
            System.out.println("User Authenticated: " + authentication.getAuthorities());
            return userDetailsService.loadUserByUsername(email);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Authentication Failed: " + e.getMessage());
            throw e;
        }
    }

    @Override
    public String generateToken(UserDetails userDetails) {
        Map<String, Object> claims = new HashMap<>();

        return Jwts.builder()
                .setClaims(claims)
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + jwtExpiresMs))
                .signWith(getSigningkey(), SignatureAlgorithm.HS256)
                .compact();
    }

    @Override
    public UserDetails validateToken(String token) {
        String username = extractUserName(token);
        return userDetailsService.loadUserByUsername(username);
    }

    private Key getSigningkey() {
        byte[] keyBytes = secretKey.getBytes();
        return Keys.hmacShaKeyFor(keyBytes);
    }

    private String extractUserName(String token) {
        Claims claims = Jwts
                .parserBuilder()
                .setSigningKey(getSigningkey())
                .build()
                .parseClaimsJws(token)
                .getBody();
        return claims.getSubject();
    }
}
