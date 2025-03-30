package com.sougata.domainApp.master.mapper;

import com.sougata.domainApp.master.dto.DistrictDto;
import com.sougata.domainApp.master.entity.CityEntity;
import com.sougata.domainApp.master.entity.DistrictEntity;

public class CityMapper {
    public static DistrictDto.CityDto toDto(CityEntity entity) {
        return DistrictDto.CityDto.builder()
                .cityId(entity.getCityId())
                .strCityName(entity.getStrCityName())
                .isActive(entity.getIsActive())
                .build();
    }


    public static CityEntity toEntity(DistrictDto.CityDto dto, DistrictEntity district) {
        return CityEntity.builder()
                .cityId(dto.cityId())
                .strCityName(dto.strCityName())
                .isActive(dto.isActive())
                .district(district)
                .build();
    }
}
