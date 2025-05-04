package com.sougata.domainApp.employeeRoleMap.service;

import com.sougata.domainApp.employeeRoleMap.dto.EmployeeRoleMapDto;

import java.util.List;

public interface EmployeeRoleMapService {
    List<EmployeeRoleMapDto> findEmployeeRoleMapByEmployeeId(Long employeeId);

    EmployeeRoleMapDto findEmployeeRoleMapById(Long id);

    EmployeeRoleMapDto createEmployeeRoleMap(EmployeeRoleMapDto dto);

    EmployeeRoleMapDto updateEmployeeRoleMap(EmployeeRoleMapDto dto);

    EmployeeRoleMapDto deleteEmployeeRoleMap(Long employeeId, Long roleId);
}
