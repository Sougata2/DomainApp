package com.sougata.domainApp.auth.service;

import org.springframework.security.core.userdetails.UserDetails;

public interface AuthService {
    UserDetails authenticate(String email, String password);

    String generateToken(UserDetails userDetails);

    UserDetails validateToken(String token);
}
