package com.sougata.domainApp.employeeRoleMap.service.impl;

import com.sougata.domainApp.auth.repository.RoleRepository;
import com.sougata.domainApp.employee.entity.EmployeeEntity;
import com.sougata.domainApp.employee.repository.EmployeeRepository;
import com.sougata.domainApp.employeeRoleMap.dto.EmployeeRoleMapDto;
import com.sougata.domainApp.employeeRoleMap.entity.EmployeeRoleMapEntity;
import com.sougata.domainApp.employeeRoleMap.mapper.EmployeeRoleMapMapping;
import com.sougata.domainApp.employeeRoleMap.repository.EmployeeRoleMapRepository;
import com.sougata.domainApp.employeeRoleMap.service.EmployeeRoleMapService;
import com.sougata.domainApp.role.entity.RoleEntity;
import com.sougata.domainApp.role.repository.EmpRoleRepository;
import com.sougata.domainApp.shared.EntityDtoMapping;
import com.sougata.domainApp.shared.RelationMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EmployeeRoleMapServiceImpl implements EmployeeRoleMapService {
    private final EntityDtoMapping entityDtoMapping;
    private final EmployeeRoleMapRepository repository;
    private final EmployeeRepository employeeRepository;
    private final EmpRoleRepository roleRepository;

    @Override
    public List<EmployeeRoleMapDto> findEmployeeRoleMapByEmployeeId(Long employeeId) {
        List<EmployeeRoleMapEntity> mappings = repository.findEmployeeRoleMapByEmployeeId(employeeId);
        return mappings.stream().map(m -> (EmployeeRoleMapDto) RelationMapper.mapToDto(m, entityDtoMapping.getEntityDtoMap())).toList();
    }

    @Override
    public EmployeeRoleMapDto findEmployeeRoleMapById(Long id) {
        Optional<EmployeeRoleMapEntity> dbEntity = repository.findById(id);
        return dbEntity
                .map(
                        employeeRoleMapEntity -> (EmployeeRoleMapDto) RelationMapper.mapToDto(employeeRoleMapEntity, entityDtoMapping.getEntityDtoMap())
                ).orElse(null);
    }

    @Override
    public EmployeeRoleMapDto createEmployeeRoleMap(EmployeeRoleMapDto dto) {
        EmployeeRoleMapEntity entity = (EmployeeRoleMapEntity) RelationMapper.mapToEntity(dto, entityDtoMapping.getDtoEntityMap());
        Optional<EmployeeEntity> employee = employeeRepository.findById(dto.getEmployee().getId());
        if (employee.isEmpty()) return null;
        Optional<RoleEntity> role = roleRepository.findById(dto.getRole().getId());
        if (role.isEmpty()) return null;
        entity.setEmployee(employee.get());
        entity.setRole(role.get());
        EmployeeRoleMapEntity savedEntity = repository.save(entity);
        return (EmployeeRoleMapDto) RelationMapper.mapToDto(savedEntity, entityDtoMapping.getEntityDtoMap());
    }

    @Override
    public EmployeeRoleMapDto updateEmployeeRoleMap(EmployeeRoleMapDto dto) {
        Optional<EmployeeRoleMapEntity> ogEntity = repository.findById(dto.getId());
        if (ogEntity.isEmpty()) return null;
        EmployeeRoleMapEntity nuEntity = (EmployeeRoleMapEntity) RelationMapper.mapToEntity(dto, entityDtoMapping.getDtoEntityMap());
        EmployeeRoleMapEntity mergedEntity = (EmployeeRoleMapEntity) RelationMapper.merge(ogEntity.get(), nuEntity);
        EmployeeRoleMapEntity savedEntity = repository.save(mergedEntity);
        return (EmployeeRoleMapDto) RelationMapper.mapToDto(savedEntity, entityDtoMapping.getEntityDtoMap());
    }

    @Override
    public EmployeeRoleMapDto deleteEmployeeRoleMap(Long employeeId, Long roleId) {
        Optional<EmployeeRoleMapEntity> ogEntity = repository.findEmployeeRoleMapByEmployeeIdAndRoleId(employeeId, roleId);
        if (ogEntity.isEmpty()) return null;
        ogEntity.get().setIsValid(0);
        EmployeeRoleMapEntity savedEntity = repository.save(ogEntity.get());
        return (EmployeeRoleMapDto) RelationMapper.mapToDto(savedEntity, entityDtoMapping.getEntityDtoMap());
    }
}
