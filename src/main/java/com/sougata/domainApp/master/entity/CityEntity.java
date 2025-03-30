package com.sougata.domainApp.master.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Filter;
import org.hibernate.annotations.FilterDef;
import org.hibernate.annotations.ParamDef;

import java.util.UUID;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "cities")
public class CityEntity {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID cityId;

    @Column
    private String strCityName;

    @Column
    private Integer isActive;

    @ManyToOne
    @JoinColumn(name = "dist_id")
    private DistrictEntity district;

    @PrePersist
    protected void onCreate() {
        isActive = 1;
    }

    @Override
    public String toString() {
        return "CityEntity{" +
                "cityId=" + cityId +
                ", strCityName='" + strCityName + '\'' +
                ", isActive=" + isActive +
                '}';
    }
}
//DistrictEntity{
//    distId=6453cecd-587d-4a41-b771-21ba80714774,
//        strDistName='New Delhi', isActive=1,
//        cities=[
//                CityEntity{cityId=7928dab7-9569-4f40-8f6a-172aba383940, strCityName='Gole Market', isActive=1},
//    CityEntity{cityId=edd3b2d9-f9da-4399-b7af-73025119bc8b, strCityName='PritamPura', isActive=1},
//    CityEntity{cityId=2e85c0c5-1fcd-47f3-95c7-9212c0e43936, strCityName='Noida', isActive=1},
//    CityEntity{cityId=8f12dc17-1f74-4762-8ea6-101f906038a6, strCityName='Gurgaon', isActive=0}, CityEntity{cityId=cb760a76-83ca-4603-8ae4-68b2fafe58b0, strCityName='Milligram', isActive=0}, CityEntity{cityId=8c01a630-98b1-4295-a77c-a75e89235505, strCityName='Subhas Nagar', isActive=0}, CityEntity{cityId=392ad912-fe1c-4cc2-9c0d-c8e20d7bb668, strCityName='Dholakuan', isActive=0}, CityEntity{cityId=cca6832a-250f-4548-9162-c36a5776de61, strCityName='Kirti Nagar', isActive=0}, CityEntity{cityId=ad908a78-2176-4ff0-83aa-e2cc0367d3a4, strCityName='Mukherjee Nagar', isActive=0}, CityEntity{cityId=08240438-01a8-42f0-b7a7-9c04f17b8f94, strCityName='Narela', isActive=0}, CityEntity{cityId=214a4af1-48f0-4f28-9494-3d2e907b9d1b, strCityName='South Delhi', isActive=1}]}
