package com.sougata.domainApp.master.service.impl;

import com.sougata.domainApp.master.dto.DistrictDto;
import com.sougata.domainApp.master.entity.DistrictEntity;
import com.sougata.domainApp.master.mapper.EntityDtoMapping;
import com.sougata.domainApp.master.repository.DistrictRepository;
import com.sougata.domainApp.master.service.DistrictService;
import com.sougata.domainApp.shared.RelationMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DistrictServiceImpl implements DistrictService {
    private final DistrictRepository repository;
    private final EntityDtoMapping entityDtoMapping;

    @Override
    public List<DistrictDto> findAllActiveDistricts() {
        return repository.findAllActiveDistricts()
                .stream().map(e ->
                        (DistrictDto) RelationMapper.mapToDto(e, entityDtoMapping.getEntityDtoMap())
                )
                .toList();
    }

    @Override
    @Transactional
    public DistrictDto createDistrict(DistrictDto dto) {
        DistrictEntity entity = (DistrictEntity) RelationMapper.mapToEntity(dto, entityDtoMapping.getDtoEntityMap());
        DistrictEntity savedEntity = repository.save(entity);
        return (DistrictDto) RelationMapper.mapToDto(savedEntity, entityDtoMapping.getEntityDtoMap());
    }

    @Override
    public List<DistrictDto> findAllMappedDistricts() {
        return repository.findMappedDistricts().stream()
                .map(e ->
                        (DistrictDto) RelationMapper.mapToDto(e, entityDtoMapping.getEntityDtoMap())
                )
                .toList();
    }

    @Override
    public DistrictDto findDistrictById(Long id) {
        Optional<DistrictEntity> found = repository.findDistrictById(id);
        return found.map(
                        e -> (DistrictDto) RelationMapper.mapToDto(e, entityDtoMapping.getEntityDtoMap())
                )
                .orElse(null);
    }

    @Override
    public DistrictDto updateDistrict(DistrictDto dto) {
        Optional<DistrictEntity> dbEntity = repository.findDistrictById(dto.getId());
        if (dbEntity.isEmpty()) return null;
        DistrictEntity nuEntity = (DistrictEntity) RelationMapper.mapToEntity(dto, entityDtoMapping.getDtoEntityMap());
        DistrictEntity merged = (DistrictEntity) RelationMapper.merge(dbEntity.get(), nuEntity);
        DistrictEntity updated = repository.save(merged);
        return (DistrictDto) RelationMapper.mapToDto(updated, entityDtoMapping.getEntityDtoMap());
    }
}
