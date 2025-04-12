package com.sougata.domainApp.master.service;

import com.sougata.domainApp.master.dto.DistrictDto;

import java.util.List;

public interface DistrictService {
    List<DistrictDto> findAllActiveDistricts();

    DistrictDto createDistrict(DistrictDto dto);
    
    List<DistrictDto> findAllMappedDistricts();
}
