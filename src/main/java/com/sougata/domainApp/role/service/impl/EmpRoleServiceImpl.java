package com.sougata.domainApp.role.service.impl;

import com.sougata.domainApp.role.dto.RoleDto;
import com.sougata.domainApp.role.entity.RoleEntity;
import com.sougata.domainApp.role.mapper.RoleEntityDtoMapping;
import com.sougata.domainApp.role.repository.EmpRoleRepository;
import com.sougata.domainApp.role.service.EmpRoleService;
import com.sougata.domainApp.shared.RelationMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EmpRoleServiceImpl implements EmpRoleService {
    private final EmpRoleRepository repository;
    private final RoleEntityDtoMapping entityDtoMapping;

    @Override
    public RoleDto createRole(RoleDto dto) {
        RoleEntity entity = (RoleEntity) RelationMapper.mapToEntity(dto, entityDtoMapping.getDtoEntityMap());
        RoleEntity saved = repository.save(entity);
        return (RoleDto) RelationMapper.mapToDto(saved, entityDtoMapping.getEntityDtoMap());
    }

    @Override
    public List<RoleDto> getAllActiveRoles() {
        return repository.findAllActiveRoles()
                .stream()
                .map(e ->
                        (RoleDto) RelationMapper.mapToDto(e, entityDtoMapping.getEntityDtoMap())
                ).toList();
    }

    @Override
    public RoleDto updateRole(RoleDto dto) {
        Optional<RoleEntity> dbEntity = repository.findById(dto.getId());
        if (dbEntity.isEmpty()) {
            return null;
        }
        RoleEntity nuEntity = (RoleEntity) RelationMapper.mapToEntity(dto, entityDtoMapping.getDtoEntityMap());
        RoleEntity merged = (RoleEntity) RelationMapper.merge(dbEntity.get(), nuEntity);
        RoleEntity saved = repository.save(merged);
        return (RoleDto) RelationMapper.mapToDto(saved, entityDtoMapping.getEntityDtoMap());
    }

    @Override
    public RoleDto getRoleById(Long id) {
        Optional<RoleEntity> dbEntity = repository.findRoleWithEmployeeById(id);
        return dbEntity
                .map(
                        roleEntity ->
                                (RoleDto) RelationMapper.mapToDto(roleEntity, entityDtoMapping.getEntityDtoMap())
                ).orElse(null);
    }
}
