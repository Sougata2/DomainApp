package com.sougata.domainApp.master.service;

import com.sougata.domainApp.master.dto.DistrictDto;

import java.util.List;
import java.util.UUID;

public interface DistrictService {
    List<DistrictDto> findAllDistrictsWithCity();

    DistrictDto findDistrictWithActiveCities(UUID id);

    DistrictDto create(DistrictDto district);

    DistrictDto update(DistrictDto district);

    DistrictDto delete(DistrictDto district);
}
