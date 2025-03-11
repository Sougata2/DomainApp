package com.sougata.domainApp.auth.security;

import com.sougata.domainApp.User.domain.entities.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.UUID;


@RequiredArgsConstructor
public class DomainUserDetail implements UserDetails {
    private final User user;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return user
                .getRoles()
                .stream()
                .map(
                        role -> new SimpleGrantedAuthority(role.getName())
                ).toList();
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getEmail();
    }

    public UUID getId() {
        return user.getId();
    }
}
