package com.sougata.domainApp.master.service.impl;

import com.sougata.domainApp.master.dto.DistrictDto;
import com.sougata.domainApp.master.entity.CityEntity;
import com.sougata.domainApp.master.entity.DistrictEntity;
import com.sougata.domainApp.master.repository.CityRepository;
import com.sougata.domainApp.master.service.CityService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CityServiceImpl implements CityService {
    private final CityRepository cityRepository;

    @Override
    @Transactional
    public DistrictDto.CityDto createCity(DistrictDto.CityDto dto, DistrictEntity district) {
        CityEntity saved = cityRepository.save(
                CityEntity.builder()
                        .strCityName(dto.strCityName())
                        .district(district)
                        .build()
        );
        return DistrictDto.CityDto.builder()
                .cityId(saved.getCityId())
                .strCityName(saved.getStrCityName())
                .isActive(saved.getIsActive())
                .build();
    }

    @Override
    public DistrictDto.CityDto getCityById(UUID id) {
        Optional<CityEntity> dbCity = cityRepository.findById(id);
        return dbCity.map(
                        cityEntity ->
                                DistrictDto.CityDto.builder()
                                        .cityId(cityEntity.getCityId())
                                        .strCityName(cityEntity.getStrCityName())
                                        .isActive(cityEntity.getIsActive())
                                        .build()
                )
                .orElse(null);
    }

    @Override
    @Transactional
    public DistrictDto.CityDto updateCity(DistrictDto.CityDto dto) {
        Optional<CityEntity> cityEntity = cityRepository.findById(dto.cityId());
        if (cityEntity.isEmpty()) return null;
        cityEntity.get().setStrCityName(dto.strCityName());
        cityEntity.get().setIsActive(dto.isActive());
        CityEntity saved = cityRepository.save(cityEntity.get());
        return DistrictDto.CityDto.builder()
                .cityId(saved.getCityId())
                .strCityName(saved.getStrCityName())
                .isActive(saved.getIsActive())
                .build();
    }

    @Override
    @Transactional
    public DistrictDto.CityDto deleteCity(UUID id) {
        Optional<CityEntity> cityEntity = cityRepository.findById(id);
        if (cityEntity.isPresent()) {
            cityEntity.get().setIsActive(0);
            CityEntity savedCity = cityRepository.save(cityEntity.get());
            return DistrictDto.CityDto.builder()
                    .cityId(savedCity.getCityId())
                    .strCityName(savedCity.getStrCityName())
                    .isActive(savedCity.getIsActive())
                    .build();
        }
        return null;
    }


}
