package com.sougata.domainApp.master.service;

import com.sougata.domainApp.master.dto.CityDto;

import java.util.List;

public interface CityService {
    List<CityDto> findAllActiveCities();

    CityDto createCity(CityDto city);
}
