package com.sougata.domainApp.role.controller;

import com.sougata.domainApp.role.dto.RoleDto;
import com.sougata.domainApp.role.service.EmpRoleService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/role")
public class RoleController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private final EmpRoleService service;

    @GetMapping
    public ResponseEntity<List<RoleDto>> getAllActiveRoles() {
        logger.info("getAllActiveRoles");
        try {
            return ResponseEntity.ok(service.getAllActiveRoles());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<RoleDto> getRoleById(@PathVariable() Long id) {
        logger.info("getRoleById id={}", id);
        try {
            RoleDto roleDto = service.getRoleById(id);
            if (roleDto == null) {
                logger.warn("getRoleById role id={} not found", id);
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.ok(roleDto);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @PostMapping
    public ResponseEntity<RoleDto> createRole(@RequestBody RoleDto dto) {
        logger.info("create role: {}", dto);
        try {
            RoleDto result = service.createRole(dto);
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @PutMapping
    public ResponseEntity<RoleDto> updateRole(@RequestBody RoleDto dto) {
        logger.info("update role: {}", dto);
        try {
            return ResponseEntity.ok(service.updateRole(dto));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
