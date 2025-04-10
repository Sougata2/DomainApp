package com.sougata.domainApp.master.entity;

import com.sougata.domainApp.shared.MasterEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "cities")
public class CityEntity implements MasterEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String cityName;

    @Column
    private Integer isValid;

    @Column
    private LocalDateTime logDate;

    @ManyToOne
    @JoinColumn(name = "dist_id")
    private DistrictEntity district;

    @PrePersist
    protected void onCreate() {
        isValid = 1;
        logDate = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        logDate = LocalDateTime.now();
    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public String toString() {
        return "CityEntity{" +
                "id=" + id +
                ", cityName='" + cityName + '\'' +
                ", isValid=" + isValid +
                ", logDate=" + logDate +
                ", district=" + district.getDistName() +
                '}';
    }
}
