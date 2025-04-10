package com.sougata.domainApp.master.entity;

import com.sougata.domainApp.shared.MasterEntity;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "districts")
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class DistrictEntity implements MasterEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private String distName;

    @Column
    private Integer isValid;

    @Column
    private LocalDateTime logDate;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "district")
    private List<CityEntity> cities;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "state_id")
    private StateEntity state;

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
}
