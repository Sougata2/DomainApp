package com.sougata.domainApp.master.dto;

import com.sougata.domainApp.master.entity.DistrictEntity;
import com.sougata.domainApp.shared.MasterDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

/**
 * DTO for {@link com.sougata.domainApp.master.entity.StateEntity}
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class StateDto implements MasterDto, Serializable {
    private Long id;
    private String stateName;
    private Integer isValid;
    private LocalDateTime logDate;
    private List<DistrictDto> districts;
}