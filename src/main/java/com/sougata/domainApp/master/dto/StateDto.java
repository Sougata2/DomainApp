package com.sougata.domainApp.master.dto;

import lombok.*;

import java.io.Serializable;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class StateDto {
    UUID id;
    String stateName;
    Integer isActive;
}