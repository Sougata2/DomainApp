package com.sougata.domainApp.role.service;

import com.sougata.domainApp.role.dto.RoleDto;

import java.util.List;

public interface EmpRoleService {
    RoleDto createRole(RoleDto dto);

    List<RoleDto> getAllActiveRoles();

    RoleDto updateRole(RoleDto dto);

    RoleDto getRoleById(Long id);
}
