package com.sougata.domainApp.master.dto;

import lombok.Builder;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;

/**
 * DTO for {@link com.sougata.domainApp.master.entity.DistrictEntity}
 */
@Builder
public record DistrictDto(UUID distId, String strDistName, Integer isActive,
                          List<CityDto> cities) implements Serializable {
    /**
     * DTO for {@link com.sougata.domainApp.master.entity.CityEntity}
     */
    @Builder
    public record CityDto(UUID cityId, String strCityName, Integer isActive) implements Serializable {
    }
}