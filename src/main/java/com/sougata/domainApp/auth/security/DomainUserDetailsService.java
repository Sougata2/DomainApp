package com.sougata.domainApp.auth.security;

import com.sougata.domainApp.User.domain.entities.User;
import com.sougata.domainApp.auth.repository.AuthRepository;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class DomainUserDetailsService implements UserDetailsService {
    private final AuthRepository authRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = authRepository.getByUserNameWithRoles(username);
        return user.map(DomainUserDetail::new).orElseThrow(() -> new UsernameNotFoundException(username));
    }
}
