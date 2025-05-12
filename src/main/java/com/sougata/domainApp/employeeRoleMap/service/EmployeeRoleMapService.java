package com.sougata.domainApp.employeeRoleMap.service;

import com.sougata.domainApp.employeeRoleMap.dto.EmployeeRoleMapDto;
import com.sougata.domainApp.role.dto.RoleDto;
import com.sougata.domainApp.role.entity.RoleEntity;

import java.util.List;
import java.util.Optional;

public interface EmployeeRoleMapService {
    List<EmployeeRoleMapDto> findEmployeeRoleMapByEmployeeId(Long employeeId);

    EmployeeRoleMapDto findEmployeeRoleMapById(Long id);

    EmployeeRoleMapDto createEmployeeRoleMap(EmployeeRoleMapDto dto);

    EmployeeRoleMapDto updateEmployeeRoleMap(EmployeeRoleMapDto dto);

    EmployeeRoleMapDto deleteEmployeeRoleMap(Long employeeId, Long roleId);

    List<RoleDto> findNotAssignedRolesByEmployeeId(Long employeeId);

    List<EmployeeRoleMapDto> createEmployeeRoleMapBulk(List<EmployeeRoleMapDto> dtos);

    RoleDto findDefaultRoleByEmployeeId(Long employeeId);
}
