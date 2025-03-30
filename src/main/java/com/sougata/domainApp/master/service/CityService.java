package com.sougata.domainApp.master.service;

import com.sougata.domainApp.master.dto.DistrictDto;
import com.sougata.domainApp.master.entity.DistrictEntity;

import java.util.UUID;

public interface CityService {
    DistrictDto.CityDto createCity(DistrictDto.CityDto dto, DistrictEntity district);

    DistrictDto.CityDto getCityById(UUID id);

    DistrictDto.CityDto updateCity(DistrictDto.CityDto dto);

    DistrictDto.CityDto deleteCity(UUID id);
}
