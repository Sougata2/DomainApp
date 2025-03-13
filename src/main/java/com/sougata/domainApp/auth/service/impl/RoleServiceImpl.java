package com.sougata.domainApp.auth.service.impl;

import com.sougata.domainApp.auth.domain.entity.Role;
import com.sougata.domainApp.auth.repository.RoleRepository;
import com.sougata.domainApp.auth.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {
    private final RoleRepository roleRepository;

    @Override
    public void create(String roleName) {
        roleRepository.save(
                Role.builder()
                        .name("ROLE_" + roleName)
                        .build()
        );
    }
}
