package com.sougata.domainApp.employee.service;

import com.sougata.domainApp.employee.dto.EmployeeDto;

import java.util.List;

public interface EmployeeService {
    EmployeeDto createEmployee(EmployeeDto employeeDto);

    List<EmployeeDto> getAllActiveEmployees();

    EmployeeDto updateEmployee(EmployeeDto dto);
}
