package com.sougata.domainApp.employeeRoleMap.service.impl;

import com.sougata.domainApp.employeeRoleMap.dto.EmployeeRoleMapDto;
import com.sougata.domainApp.employeeRoleMap.entity.EmployeeRoleMapEntity;
import com.sougata.domainApp.employeeRoleMap.mapper.EmployeeRoleMapMapping;
import com.sougata.domainApp.employeeRoleMap.repository.EmployeeRoleMapRepository;
import com.sougata.domainApp.employeeRoleMap.service.EmployeeRoleMapService;
import com.sougata.domainApp.shared.RelationMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EmployeeRoleMapServiceImpl implements EmployeeRoleMapService {
    private final EmployeeRoleMapMapping entityDtoMapping;
    private final EmployeeRoleMapRepository repository;

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
