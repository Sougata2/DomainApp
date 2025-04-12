package com.sougata.domainApp.master.service;

import com.sougata.domainApp.master.dto.CityDto;
import com.sougata.domainApp.master.dto.DistrictDto;

import java.util.List;

public interface CityService {
    List<CityDto> findAllActiveCities();

    CityDto createCity(CityDto city);

    List<CityDto> findCitiesNotMappedToDist(DistrictDto district);
}
