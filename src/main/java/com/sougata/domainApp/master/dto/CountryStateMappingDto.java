package com.sougata.domainApp.master.dto;

import com.sougata.domainApp.master.entity.CountryEntity;
import com.sougata.domainApp.master.entity.StateEntity;
import lombok.*;

import java.io.Serializable;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CountryStateMappingDto {
    UUID id;
    CountryEntity country;
    StateEntity state;
    Integer isValid;
}