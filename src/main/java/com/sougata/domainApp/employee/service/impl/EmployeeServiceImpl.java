package com.sougata.domainApp.employee.service.impl;

import com.sougata.domainApp.employee.dto.EmployeeDto;
import com.sougata.domainApp.employee.entity.EmployeeEntity;
import com.sougata.domainApp.employee.mapper.EmployeeEntityDtoMapping;
import com.sougata.domainApp.employee.repository.EmployeeRepository;
import com.sougata.domainApp.employee.service.EmployeeService;
import com.sougata.domainApp.shared.RelationMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {
    private final EmployeeRepository repository;
    private final EmployeeEntityDtoMapping entityDtoMapping;

    @Override
    public EmployeeDto createEmployee(EmployeeDto dto) {
        EmployeeEntity entity = (EmployeeEntity) RelationMapper.mapToEntity(dto, entityDtoMapping.getDtoEntityMap());
        EmployeeEntity savedEntity = repository.save(entity);
        return (EmployeeDto) RelationMapper.mapToDto(savedEntity, entityDtoMapping.getEntityDtoMap());
    }

    @Override
    public List<EmployeeDto> getAllActiveEmployees() {
        return repository.getAllActiveEmployees()
                .stream()
                .map(e ->
                        (EmployeeDto) RelationMapper.mapToDto(e, entityDtoMapping.getEntityDtoMap())
                ).toList();
    }

    @Override
    public EmployeeDto updateEmployee(EmployeeDto dto) {
        Optional<EmployeeEntity> dbEntity = repository.findById(dto.getId());
        if (dbEntity.isEmpty()) {
            return null;
        }
        EmployeeEntity nuEntity = (EmployeeEntity) RelationMapper.mapToEntity(dto, entityDtoMapping.getDtoEntityMap());
        EmployeeEntity merged = (EmployeeEntity) RelationMapper.mergeMTM(dbEntity.get(), nuEntity, 1);
        EmployeeEntity savedEntity = repository.save(merged);
        return (EmployeeDto) RelationMapper.mapToDto(savedEntity, entityDtoMapping.getEntityDtoMap());
    }

    @Override
    public EmployeeDto getEmployeeById(Long id) {
        Optional<EmployeeEntity> dbEntity = repository.findEmployeeWithRolesById(id);
        if (dbEntity.isEmpty()) {
            return null;
        }
        return dbEntity
                .map(
                        employeeEntity ->
                                (EmployeeDto) RelationMapper.mapToDto(employeeEntity, entityDtoMapping.getEntityDtoMap())
                ).orElse(null);
    }
}
