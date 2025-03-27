package com.sougata.domainApp.master.dto;

import lombok.*;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CountryDto {
    private UUID id;
    private String countryName;
    private Integer isActive;

    @Override
    public String toString() {
        return "CountryDto{" +
                "id=" + id +
                ", countryName='" + countryName + '\'' +
                ", isActive=" + isActive +
                '}';
    }
}