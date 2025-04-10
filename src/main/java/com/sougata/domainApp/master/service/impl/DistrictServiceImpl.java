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
}
