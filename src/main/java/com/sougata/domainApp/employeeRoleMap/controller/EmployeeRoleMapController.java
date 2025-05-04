package com.sougata.domainApp.employeeRoleMap.controller;

import com.sougata.domainApp.employeeRoleMap.dto.EmployeeRoleMapDto;
import com.sougata.domainApp.employeeRoleMap.service.EmployeeRoleMapService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/employee-role-map")
public class EmployeeRoleMapController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private final EmployeeRoleMapService service;

    @GetMapping
    public ResponseEntity<List<EmployeeRoleMapDto>> getEmployeeRoleMapByEmployeeId(@RequestParam Long employeeId) {
        logger.info("getEmployeeRoleMapByEmployeeId: {}", employeeId);
        try {
            List<EmployeeRoleMapDto> dto = service.findEmployeeRoleMapByEmployeeId(employeeId);
            return ResponseEntity.ok(dto);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @PostMapping
    public ResponseEntity<EmployeeRoleMapDto> createEmployeeRoleMap(@RequestBody EmployeeRoleMapDto dto) {
        logger.info("createEmployeeRoleMap: {}", dto);
        try {
            EmployeeRoleMapDto created = service.createEmployeeRoleMap(dto);
            return ResponseEntity.ok(created);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @DeleteMapping
    public ResponseEntity<EmployeeRoleMapDto> deleteEmployeeRoleMap(@RequestParam Long employeeId, @RequestParam Long roleId) {
        logger.info("deleteEmployeeRoleMap: {}", employeeId);
        try {
            EmployeeRoleMapDto deleted = service.deleteEmployeeRoleMap(employeeId, roleId);
            if (deleted == null) {
                logger.error("deleteEmployeeRoleMap with employeeId {} and roleId {} not found", employeeId, roleId);
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            return ResponseEntity.ok(deleted);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
