package com.sougata.domainApp.master.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "districts")
public class DistrictEntity {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID distId;

    @Column
    private String strDistName;

    @Column
    private Integer isActive;

    @OneToMany(mappedBy = "district", fetch = FetchType.LAZY)
    private List<CityEntity> cities = new ArrayList<>();


    @PrePersist
    protected void onCreate() {
        isActive = 1;
    }

    @Override
    public String toString() {
        return "DistrictEntity{" +
                "distId=" + distId +
                ", strDistName='" + strDistName + '\'' +
                ", isActive=" + isActive +
                ", cities=" + cities +
                '}';
    }
}
