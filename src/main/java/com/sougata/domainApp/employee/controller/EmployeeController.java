package com.sougata.domainApp.employee.controller;

import com.sougata.domainApp.employee.dto.EmployeeDto;
import com.sougata.domainApp.employee.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/employee")
public class EmployeeController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private final EmployeeService service;


    @GetMapping
    public List<EmployeeDto> getAllActiveEmployees() {
        logger.info("Get all active employees");
        try {
            return service.getAllActiveEmployees();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @PostMapping
    public EmployeeDto createEmployee(@RequestBody EmployeeDto dto) {
        logger.info("Create employee : {}", dto);
        try {
            return service.createEmployee(dto);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @PutMapping
    public ResponseEntity<EmployeeDto> updateEmployee(@RequestBody EmployeeDto dto) {
        logger.info("Update employee : {}", dto);
        try {
            EmployeeDto updatedEmployee = service.updateEmployee(dto);
            return ResponseEntity.ok(updatedEmployee);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
