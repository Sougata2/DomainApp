package com.sougata.domainApp.auth.security;

import com.sougata.domainApp.auth.service.AuthService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    private final AuthService authService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        //SO ACTUALLY WHAT IS HAPPENING HERE
        // 1. get the token from the request
        // 2. validating the token (extracting the username from the token and loading the matching user from db)
        // 3. create a authToken[UsernamePasswordAuthenticationToken] for  the security context
        // 4. pass the authToken to the security context holder
        // 5. security context holder will check if the user have access to the resource mentioned in request matcher
        // 6. if the user does not have the proper access then throw error(403 forbidden).
        // 7. else carry on.

        String token = extractToken(request);
        if (token != null) {
            UserDetails userDetails = authService.validateToken(token);
            UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authentication);
            if (userDetails instanceof DomainUserDetail) {
                request.setAttribute("userId", ((DomainUserDetail) userDetails).getId());
            }
        }

        filterChain.doFilter(request, response);
    }

    private String extractToken(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");
        if (bearerToken == null) return null;
        if (!bearerToken.startsWith("Bearer ")) return null;
        return bearerToken.split(" ")[1];
    }
}
