package com.sougata.domainApp.master.dto;

import com.sougata.domainApp.master.entity.CityEntity;
import com.sougata.domainApp.master.entity.StateEntity;
import com.sougata.domainApp.shared.MasterDto;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

/**
 * DTO for {@link com.sougata.domainApp.master.entity.DistrictEntity}
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class DistrictDto implements Serializable, MasterDto {
    private Long id;
    private String distName;
    private Integer isValid;
    private LocalDateTime logDate;
    private List<CityDto> cities;
    private StateDto state;
}