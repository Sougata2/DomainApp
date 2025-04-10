package com.sougata.domainApp.master.service.impl;

import com.sougata.domainApp.master.dto.CityDto;
import com.sougata.domainApp.master.entity.CityEntity;
import com.sougata.domainApp.master.mapper.EntityDtoMapping;
import com.sougata.domainApp.master.repository.CityRepository;
import com.sougata.domainApp.master.service.CityService;
import com.sougata.domainApp.shared.RelationMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CityServiceImpl implements CityService {
    private final CityRepository repository;
    private final EntityDtoMapping entityDtoMapping;

    @Override
    public List<CityDto> findAllActiveCities() {
        return repository.findAllActiveCities()
                .stream()
                .map(e ->
                        (CityDto) RelationMapper.mapToDto(e, entityDtoMapping.getEntityDtoMap())
                ).toList();
    }

    @Override
    @Transactional
    public CityDto createCity(CityDto city) {
        CityEntity entity = (CityEntity) RelationMapper.mapToEntity(city, entityDtoMapping.getDtoEntityMap());
        CityEntity cityEntity = repository.save(entity);
        return (CityDto) RelationMapper.mapToDto(cityEntity, entityDtoMapping.getEntityDtoMap());
    }
}
