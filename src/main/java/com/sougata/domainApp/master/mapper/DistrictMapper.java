package com.sougata.domainApp.master.mapper;

import com.sougata.domainApp.master.dto.DistrictDto;
import com.sougata.domainApp.master.entity.DistrictEntity;

public class DistrictMapper {
    public static DistrictDto toDto(DistrictEntity entity) {
        return DistrictDto.builder()
                .distId(entity.getDistId())
                .strDistName(entity.getStrDistName())
                .isActive(entity.getIsActive())
                .cities(
                        entity.getCities().stream()
                                .map(CityMapper::toDto).toList()
                )
                .build();
    }


    public static DistrictEntity toEntity(DistrictDto dto, DistrictEntity district) {
        return DistrictEntity.builder()
                .distId(dto.distId())
                .strDistName(dto.strDistName())
                .isActive(dto.isActive())
                .cities(dto.cities().stream()
                        .map(
                                c -> CityMapper.toEntity(c, district)
                        ).toList()
                )
                .build();
    }
}
