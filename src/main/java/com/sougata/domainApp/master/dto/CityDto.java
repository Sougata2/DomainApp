package com.sougata.domainApp.master.dto;

import com.sougata.domainApp.master.entity.DistrictEntity;
import com.sougata.domainApp.shared.MasterDto;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * DTO for {@link com.sougata.domainApp.master.entity.CityEntity}
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class CityDto implements Serializable, MasterDto {
    private Long id;
    private String cityName;
    private Integer isValid;
    private LocalDateTime logDate;
    private DistrictDto district;
}