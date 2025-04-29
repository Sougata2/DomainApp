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
    public ResponseEntity<List<EmployeeDto>> getAllActiveEmployees() {
        logger.info("Get all active employees");
        try {
            return ResponseEntity.ok(service.getAllActiveEmployees());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<EmployeeDto> getEmployeeById(@PathVariable Long id) {
        logger.info("Get employee by id: {}", id);
        try {
            EmployeeDto employee = service.getEmployeeById(id);
            if (employee == null) {
                logger.error("Employee with id {} not found", id);
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.ok(employee);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @PostMapping
    public ResponseEntity<EmployeeDto> createEmployee(@RequestBody EmployeeDto dto) {
        logger.info("Create employee : {}", dto);
        try {
            return ResponseEntity.ok(service.createEmployee(dto));
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
